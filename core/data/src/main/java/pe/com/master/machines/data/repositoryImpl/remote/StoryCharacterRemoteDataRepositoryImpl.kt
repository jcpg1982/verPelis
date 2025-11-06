package pe.com.master.machines.data.repositoryImpl.remote

import kotlinx.coroutines.flow.flowOf
import pe.com.master.machines.common.response.Resource
import pe.com.master.machines.data.repository.remote.MovieRemoteDataRepository
import pe.com.master.machines.model.model.Movie
import pe.com.master.machines.model.response.ResponseAllMovies
import pe.com.master.machines.network.repository.MovieNetworkRepository
import javax.inject.Inject

class MovieRemoteDataRepositoryImpl @Inject constructor(
    private val movieNetworkRepository: MovieNetworkRepository
) : MovieRemoteDataRepository {

    override fun getLoadAllCharacters(page: Int) = flowOf(Resource.Success(ResponseAllMovies()))
    /*MovieNetworkRepository.getLoadAllCharacters(page)
        .map { res ->
            when (res) {
                is Resource.Error<*> -> Resource.Error(res.error)
                is Resource.Success -> Resource.Success(res.data.asModel())
            }
        }
        .catch { emit(Resource.Error(it.toErrorType())) }*/

    override fun getLoadSingleCharacter(id: Int) = flowOf(Resource.Success(Movie()))
    /*MovieNetworkRepository.getLoadSingleCharacter(id)
        .map { res ->
            when (res) {
                is Resource.Error<*> -> Resource.Error(res.error)
                is Resource.Success -> Resource.Success(res.data.asModel())
            }
        }
        .catch { emit(Resource.Error(it.toErrorType())) }*/

    override fun searchCharacterByName(
        page: Int, name: String, status: String
    ) = flowOf(Resource.Success(ResponseAllMovies()))
    /*MovieNetworkRepository.searchCharacterByName(page, name, status)
    .map { res ->
        when (res) {
            is Resource.Error<*> -> Resource.Error(res.error)
            is Resource.Success -> Resource.Success(res.data.asModel())
        }
    }
    .catch { emit(Resource.Error(it.toErrorType())) }*/


}