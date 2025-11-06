package pe.com.master.machines.network.api

import pe.com.master.machines.network.model.model.MovieNetwork
import pe.com.master.machines.network.model.response.ResponseAllMoviesNetwork
import pe.com.master.machines.network.utils.Utils.Endpoints.ALL_MOVIES
import pe.com.master.machines.network.utils.Utils.Endpoints.SEARCH_MOVIE
import pe.com.master.machines.network.utils.Utils.Endpoints.SINGLE_MOVIE
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET(ALL_MOVIES)
    suspend fun getLoadAllCharacters(
        @Query("language") language: String = "es-ES",
        @Query("page") page: Int
    ): ResponseAllMoviesNetwork

    @GET("$SINGLE_MOVIE/{movie_id}")
    suspend fun getLoadSingleMovie(
        @Path("movie_id") movieId: Int,
        @Query("language") language: String = "es-ES"
    ): MovieNetwork?

    @GET(SEARCH_MOVIE)
    suspend fun searchMovieByName(
        @Query("page") page: Int,
        @Query("language") language: String = "es-ES",
        @Query("query") query: String
    ): ResponseAllMoviesNetwork
}