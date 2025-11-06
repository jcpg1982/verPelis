package pe.com.master.machines.data.repository.database

import kotlinx.coroutines.flow.Flow
import pe.com.master.machines.common.response.Resource
import pe.com.master.machines.model.model.Movie

interface MovieLocalDataRepository {

    fun saveAllCharacters(entities: List<Movie>): Flow<Resource<Unit>>
    fun getSingleCharacter(id: Int): Flow<Resource<Movie>>
    fun getCharactersByPage(limit: Int, offset: Int): Flow<Resource<List<Movie>>>
    fun searchCharacterByName(
        limit: Int, offset: Int, name: String, status: String
    ): Flow<Resource<List<Movie>>>
}