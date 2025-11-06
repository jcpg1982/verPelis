package pe.com.master.machines.data.repository.remote

import kotlinx.coroutines.flow.Flow
import pe.com.master.machines.common.response.Resource
import pe.com.master.machines.model.model.Movie
import pe.com.master.machines.model.response.ResponseAllMovies

interface MovieRemoteDataRepository {
    fun getLoadAllCharacters(page: Int): Flow<Resource<ResponseAllMovies>>
    fun getLoadSingleCharacter(id: Int): Flow<Resource<Movie>>
    fun searchCharacterByName(
        page: Int, name: String, status: String
    ): Flow<Resource<ResponseAllMovies>>
}