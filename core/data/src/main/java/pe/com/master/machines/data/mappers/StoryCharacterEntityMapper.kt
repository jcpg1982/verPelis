package pe.com.master.machines.data.mappers

import pe.com.master.machines.database.entity.MovieEntity
import pe.com.master.machines.model.utils.Utils.toStringList

/*
fun MovieEntity.asModel() = Movie(
    id = this.id,
    name = this.name,
    status = this.status,
    species = this.species,
    type = this.type,
    gender = this.gender,
    originName = this.originName,
    originUrl = this.originUrl,
    locationName = this.locationName,
    locationUrl = this.locationUrl,
    image = this.image,
    episode = toStringList(this.episode),
    url = this.url,
    created = this.created,
)

fun List<MovieEntity>?.asModel() = this?.map(MovieEntity::asModel) ?: listOf()*/
