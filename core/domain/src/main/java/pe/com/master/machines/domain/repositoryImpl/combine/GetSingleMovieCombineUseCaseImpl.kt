package pe.com.master.machines.domain.repositoryImpl.combine

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import pe.com.master.machines.common.response.Resource
import pe.com.master.machines.common.response.toErrorType
import pe.com.master.machines.data.repository.database.MovieLocalDataRepository
import pe.com.master.machines.data.repository.remote.MovieRemoteDataRepository
import pe.com.master.machines.domain.repository.combine.GetSingleMovieCombineUseCase
import pe.com.master.machines.model.model.Movie
import javax.inject.Inject

class GetSingleMovieCombineUseCaseImpl @Inject constructor(
    private val MovieLocalDataRepository: MovieLocalDataRepository,
    private val MovieRemoteDataRepository: MovieRemoteDataRepository,
) : GetSingleMovieCombineUseCase {

    override fun invoke(isHaveInternet: Boolean, id: Int): Flow<Resource<Movie?>> {
        val localFlow = MovieLocalDataRepository.getSingleCharacter(id)
        return if (!isHaveInternet) {
            localFlow.map { local ->
                when (local) {
                    is Resource.Success -> Resource.Success(local.data)
                    is Resource.Error -> Resource.Error(local.error)
                }
            }
        } else {
            val remoteFlow = MovieRemoteDataRepository.getLoadSingleCharacter(id)
            combine(localFlow, remoteFlow) { local, remote ->
                when {
                    remote is Resource.Success -> {
                        remote.data?.let {
                            MovieLocalDataRepository.saveAllCharacters(listOf(it))
                                .collect {}
                        }
                        Resource.Success(remote.data)
                    }

                    remote is Resource.Error && local is Resource.Success -> {
                        Resource.Success(local.data)
                    }

                    local is Resource.Success -> {
                        Resource.Success(local.data)
                    }

                    remote is Resource.Error -> Resource.Error(remote.error)
                    local is Resource.Error -> Resource.Error(local.error)
                    else -> Resource.Error(Throwable().toErrorType())
                }
            }.distinctUntilChanged()
        }
    }
}