package pe.com.master.machines.network.model.response

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable
import pe.com.master.machines.network.model.model.MovieNetwork

@Serializable
data class ResponseAllMoviesNetwork(
    @SerializedName("page")
    val page: Int? = null,
    @SerializedName("results")
    val results: List<MovieNetwork>? = null,
    @SerializedName("total_pages")
    val totalPages: Int? = null,
    @SerializedName("total_results")
    val totalResults: Int? = null
)