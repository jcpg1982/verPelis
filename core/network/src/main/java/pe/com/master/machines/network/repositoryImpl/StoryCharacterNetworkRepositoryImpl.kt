package pe.com.master.machines.network.repositoryImpl

import kotlinx.coroutines.flow.flow
import pe.com.master.machines.common.response.Resource
import pe.com.master.machines.network.api.ApiService
import pe.com.master.machines.network.repository.MovieNetworkRepository
import javax.inject.Inject

class MovieNetworkRepositoryImpl @Inject constructor(private val apiService: ApiService) :
    MovieNetworkRepository {

    override fun getLoadAllCharacters(page: Int) = flow {
        val response = apiService.getLoadAllCharacters(page)
        emit(Resource.Success(response))
    }

    override fun getLoadSingleCharacter(id: Int) = flow {
        val response = apiService.getLoadSingleCharacter(id)
        emit(Resource.Success(response))
    }

    override fun searchCharacterByName(
        page: Int,
        name: String,
        status: String
    ) = flow {
        val response = apiService.searchCharacterByName(page, name, status)
        emit(Resource.Success(response))
    }
}