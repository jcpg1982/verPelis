package pe.com.master.machines.data.repository.remote

import kotlinx.coroutines.flow.Flow
import pe.com.master.machines.common.response.Resource
import pe.com.master.machines.model.model.Movie
import pe.com.master.machines.model.response.ResponseAllMovies
import pe.com.master.machines.network.model.model.MovieNetwork
import pe.com.master.machines.network.model.response.ResponseAllMoviesNetwork

interface MovieRemoteDataRepository {

    fun getLoadAllCharacters(page: Int): Flow<Resource<ResponseAllMovies>>

    fun getLoadSingleCharacter(movieId: Int): Flow<Resource<Movie?>>

    fun searchCharacterByName(
        page: Int, query: String
    ): Flow<Resource<ResponseAllMovies>>
}