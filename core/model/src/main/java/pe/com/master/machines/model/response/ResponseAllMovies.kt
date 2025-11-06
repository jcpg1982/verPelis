package pe.com.master.machines.model.response

import pe.com.master.machines.model.model.Movie


data class ResponseAllMovies(
    val page: Int = -1,
    val results: List<Movie> = listOf(),
    val totalPages: Int = -1,
    val totalResults: Int = -1
)