package pe.com.master.machines.domain.repositoryImpl.combine

import android.nfc.tech.MifareUltralight.PAGE_SIZE
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import pe.com.master.machines.common.response.Resource
import pe.com.master.machines.common.response.toErrorType
import pe.com.master.machines.data.repository.database.MovieLocalDataRepository
import pe.com.master.machines.data.repository.preferences.PreferencesDataRepository
import pe.com.master.machines.data.repository.remote.MovieRemoteDataRepository
import pe.com.master.machines.domain.repository.combine.GetAllCharactersCombineUseCase
import pe.com.master.machines.model.response.ResponseAllMovies
import javax.inject.Inject

class GetAllCharactersCombineUseCaseImpl @Inject constructor(
    private val movieLocalDataRepository: MovieLocalDataRepository,
    private val movieRemoteDataRepository: MovieRemoteDataRepository,
    private val preferencesDataRepository: PreferencesDataRepository,
) : GetAllCharactersCombineUseCase {

    override fun invoke(
        isHaveInternet: Boolean, page: Int
    ): Flow<Resource<ResponseAllMovies>> {
        val offset = (page - 1) * PAGE_SIZE
        val localFlow = movieLocalDataRepository.getCharactersByPage(PAGE_SIZE, offset)
        return if (!isHaveInternet) {
            localFlow.map { local ->
                val totalPages = preferencesDataRepository.totalPages.firstOrNull() ?: -1
                when (local) {
                    is Resource.Success -> {
                        if (local.data.isEmpty()) {
                            Resource.Error(Throwable("No tiene internet y no hay datos almacenados").toErrorType())
                        } else {
                            Resource.Success(
                                ResponseAllMovies(
                                    results = local.data,
                                    totalPages = totalPages
                                )
                            )
                        }
                    }

                    is Resource.Error -> Resource.Error(local.error)
                }
            }
        } else {
            val remoteFlow = movieRemoteDataRepository.getLoadAllCharacters(page)
            combine(localFlow, remoteFlow) { local, remote ->
                val totalPages = preferencesDataRepository.totalPages.firstOrNull() ?: -1
                when {
                    remote is Resource.Success -> {
                        movieLocalDataRepository.saveAllCharacters(remote.data.results)
                            .collect {}
                        preferencesDataRepository.setTotalPages(remote.data.totalPages)
                        Resource.Success(remote.data)
                    }

                    remote is Resource.Error && local is Resource.Success && local.data.isNotEmpty() -> {
                        Resource.Success(
                            ResponseAllMovies(
                                results = local.data,
                                totalPages = totalPages
                            )
                        )
                    }

                    local is Resource.Success && local.data.isNotEmpty() -> {
                        Resource.Success(
                            ResponseAllMovies(
                                results = local.data,
                                totalPages = totalPages
                            )
                        )
                    }

                    remote is Resource.Error -> Resource.Error(remote.error)
                    local is Resource.Error -> Resource.Error(local.error)
                    else -> Resource.Error(Throwable().toErrorType())
                }
            }.distinctUntilChanged()
        }
    }
}
