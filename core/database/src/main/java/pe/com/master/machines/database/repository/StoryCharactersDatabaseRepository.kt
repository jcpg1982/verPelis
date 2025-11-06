package pe.com.master.machines.database.repository

import kotlinx.coroutines.flow.Flow
import pe.com.master.machines.common.response.Resource
import pe.com.master.machines.database.entity.MovieEntity

interface MoviesDatabaseRepository {

    fun saveAllCharacters(entities: List<MovieEntity>): Flow<Resource<Unit>>
    fun getSingleCharacter(id: Int): Flow<Resource<MovieEntity?>>
    fun getCharactersByPage(limit: Int, offset: Int): Flow<Resource<List<MovieEntity>>>
    fun searchCharacterByName(
        limit: Int, offset: Int, name: String, status: String
    ): Flow<Resource<List<MovieEntity>>>
}