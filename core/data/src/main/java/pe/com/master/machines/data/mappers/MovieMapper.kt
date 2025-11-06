package pe.com.master.machines.data.mappers

import pe.com.master.machines.database.entity.MovieEntity
import pe.com.master.machines.model.model.Movie
import pe.com.master.machines.model.utils.Utils.fromIntList
import pe.com.master.machines.model.utils.Utils.fromStringList

fun Movie.toEntity(): MovieEntity {
    val entity = MovieEntity()
    entity.adult = this.adult
    entity.backdropPath = this.backdropPath
    entity.genreIds = fromIntList(this.genreIds)
    entity.id = this.id
    entity.originalLanguage = this.originalLanguage
    entity.originalTitle = this.originalTitle
    entity.overview = this.overview
    entity.popularity = this.popularity
    entity.posterPath = this.posterPath
    entity.releaseDate = this.releaseDate
    entity.title = this.title
    entity.video = this.video
    entity.voteAverage = this.voteAverage
    entity.voteCount = this.voteCount
    return entity
}

fun List<Movie>?.toEntity() = this?.map(Movie::toEntity) ?: listOf()
