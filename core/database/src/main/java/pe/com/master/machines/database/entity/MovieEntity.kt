package pe.com.master.machines.database.entity

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId

open class MovieEntity : RealmObject {
    @PrimaryKey
    var id: Int = 0
    var adult: Boolean = false
    var backdropPath: String = ""
    var genreIds: String = ""
    var originalLanguage: String = ""
    var originalTitle: String = ""
    var overview: String = ""
    var popularity: Double = 0.0
    var posterPath: String = ""
    var releaseDate: String = ""
    var title: String = ""
    var video: Boolean = false
    var voteAverage: Double = 0.0
    var voteCount: Int = -1
}
