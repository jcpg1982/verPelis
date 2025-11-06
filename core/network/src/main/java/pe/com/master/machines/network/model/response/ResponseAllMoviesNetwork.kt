package pe.com.master.machines.network.model.response

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable
import pe.com.master.machines.network.model.model.MovieNetwork

@Serializable
data class ResponseAllMoviesNetwork(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<MovieNetwork>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)