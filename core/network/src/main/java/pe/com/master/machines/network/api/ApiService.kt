package pe.com.master.machines.network.api

import pe.com.master.machines.network.model.model.MovieNetwork
import pe.com.master.machines.network.model.response.ResponseAllMoviesNetwork
import pe.com.master.machines.network.utils.Utils.Endpoints.ALL_MOVIES
import pe.com.master.machines.network.utils.Utils.Endpoints.SINGLE_MOVIE
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET(ALL_MOVIES)
    suspend fun getLoadAllCharacters(@Query("page") page: Int): ResponseAllMoviesNetwork

    @GET("$SINGLE_MOVIE{id}")
    suspend fun getLoadSingleCharacter(@Path("id") id: Int): MovieNetwork

    @GET(SINGLE_MOVIE)
    suspend fun searchCharacterByName(
        @Query("page") page: Int,
        @Query("name") name: String,
        @Query("status") status: String = "alive"
    ): ResponseAllMoviesNetwork
}