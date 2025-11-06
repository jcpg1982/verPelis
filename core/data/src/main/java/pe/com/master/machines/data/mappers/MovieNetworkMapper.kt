package pe.com.master.machines.data.mappers

import pe.com.master.machines.model.model.Movie
import pe.com.master.machines.network.model.model.MovieNetwork

fun MovieNetwork.asModel() = Movie(
    adult = this.adult ?: false,
    backdropPath = this.backdropPath.orEmpty(),
    genreIds = this.genreIds.orEmpty(),
    id = this.id ?: -1,
    originalLanguage = this.originalLanguage.orEmpty(),
    originalTitle = this.originalTitle.orEmpty(),
    overview = this.overview.orEmpty(),
    popularity = this.popularity ?: 0.0,
    posterPath = this.posterPath.orEmpty(),
    releaseDate = this.releaseDate.orEmpty(),
    title = this.title.orEmpty(),
    video = this.video ?: false,
    voteAverage = this.voteAverage ?: 0.0,
    voteCount = this.voteCount ?: -1,
)

fun List<MovieNetwork>?.asModel() = this?.map(MovieNetwork::asModel) ?: listOf()
