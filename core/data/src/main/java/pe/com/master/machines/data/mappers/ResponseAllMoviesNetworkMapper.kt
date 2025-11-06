package pe.com.master.machines.data.mappers

import pe.com.master.machines.model.response.ResponseAllMovies
import pe.com.master.machines.network.model.response.ResponseAllMoviesNetwork

fun ResponseAllMoviesNetwork.asModel() = ResponseAllMovies(
    page = this.page ?: -1,
    results = this.results.asModel() ?: listOf(),
    totalPages = this.totalPages ?: -1,
    totalResults = this.totalResults ?: -1,
)
