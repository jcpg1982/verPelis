package pe.com.master.machines.network.repository

import kotlinx.coroutines.flow.Flow
import pe.com.master.machines.common.response.Resource
import pe.com.master.machines.network.model.model.MovieNetwork
import pe.com.master.machines.network.model.response.ResponseAllMoviesNetwork

interface MovieNetworkRepository {
    fun getLoadAllCharacters(page: Int): Flow<Resource<ResponseAllMoviesNetwork>>

    fun getLoadSingleCharacter(movieId: Int): Flow<Resource<MovieNetwork?>>

    fun searchCharacterByName(
        page: Int, query: String
    ): Flow<Resource<ResponseAllMoviesNetwork>>
}