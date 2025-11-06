package pe.com.master.machines.data.repositoryImpl.remote

import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import pe.com.master.machines.common.response.Resource
import pe.com.master.machines.common.response.toErrorType
import pe.com.master.machines.data.mappers.asModel
import pe.com.master.machines.data.repository.remote.MovieRemoteDataRepository
import pe.com.master.machines.model.model.Movie
import pe.com.master.machines.model.response.ResponseAllMovies
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

    override fun getLoadSingleCharacter(id: Int) = movieNetworkRepository.getLoadSingleCharacter(id)
        .map { res ->
            when (res) {
                is Resource.Error<*> -> Resource.Error(res.error)
                is Resource.Success -> Resource.Success(res.data.asModel())
            }
        }
        .catch { emit(Resource.Error(it.toErrorType())) }

    override fun searchCharacterByName(
        page: Int, name: String, status: String
    ) = movieNetworkRepository.searchCharacterByName(page, name, status)
        .map { res ->
            when (res) {
                is Resource.Error<*> -> Resource.Error(res.error)
                is Resource.Success -> Resource.Success(res.data.asModel())
            }
        }
        .catch { emit(Resource.Error(it.toErrorType())) }


}