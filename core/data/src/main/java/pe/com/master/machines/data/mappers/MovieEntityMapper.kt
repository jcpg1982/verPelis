package pe.com.master.machines.data.mappers

import pe.com.master.machines.database.entity.MovieEntity
import pe.com.master.machines.model.model.Movie
import pe.com.master.machines.model.utils.Utils.toIntList
import pe.com.master.machines.network.model.model.MovieNetwork

fun MovieEntity.asModel() = Movie(
    adult = this.adult,
    backdropPath = this.backdropPath,
    genreIds = toIntList(this.genreIds),
    id = this.id,
    originalLanguage = this.originalLanguage,
    originalTitle = this.originalTitle,
    overview = this.overview,
    popularity = this.popularity,
    posterPath = this.posterPath,
    releaseDate = this.releaseDate,
    title = this.title,
    video = this.video,
    voteAverage = this.voteAverage,
    voteCount = this.voteCount,
)

fun List<MovieEntity>?.asModel() = this?.map(MovieEntity::asModel) ?: listOf()
