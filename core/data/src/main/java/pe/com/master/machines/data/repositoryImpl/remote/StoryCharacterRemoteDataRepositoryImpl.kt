package pe.com.master.machines.data.repositoryImpl.remote

import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import pe.com.master.machines.common.response.Resource
import pe.com.master.machines.common.response.toErrorType
import pe.com.master.machines.data.mappers.asModel
import pe.com.master.machines.data.repository.remote.MovieRemoteDataRepository
import pe.com.master.machines.network.repository.MovieNetworkRepository
import javax.inject.Inject

class MovieRemoteDataRepositoryImpl @Inject constructor(
    private val movieNetworkRepository: MovieNetworkRepository
) : MovieRemoteDataRepository {

    override fun getLoadAllCharacters(page: Int) = movieNetworkRepository.getLoadAllCharacters(page)
        .map { res ->
            when (res) {
                is Resource.Error<*> -> Resource.Error(res.error)
                is Resource.Success -> Resource.Success(res.data.asModel())
            }
        }
        .catch { emit(Resource.Error(it.toErrorType())) }

    override fun getLoadSingleCharacter(movieId: Int) =
        movieNetworkRepository.getLoadSingleCharacter(movieId)
            .map { res ->
                when (res) {
                    is Resource.Error<*> -> Resource.Error(res.error)
                    is Resource.Success -> Resource.Success(res.data?.asModel())
                }
            }
            .catch { emit(Resource.Error(it.toErrorType())) }

    override fun searchCharacterByName(
        page: Int, query: String
    ) = movieNetworkRepository.searchCharacterByName(page, query)
        .map { res ->
            when (res) {
                is Resource.Error<*> -> Resource.Error(res.error)
                is Resource.Success -> Resource.Success(res.data.asModel())
            }
        }
        .catch { emit(Resource.Error(it.toErrorType())) }

}