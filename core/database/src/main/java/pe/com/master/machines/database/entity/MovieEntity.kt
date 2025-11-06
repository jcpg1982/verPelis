package pe.com.master.machines.database.entity

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId

open class MovieEntity : RealmObject {
    @PrimaryKey
    var id: ObjectId = ObjectId()
    var name: String = ""
    var status: String = ""
    var species: String = ""
    var type: String = ""
    var gender: String = ""
    var originName: String = ""
    var originUrl: String = ""
    var locationName: String = ""
    var locationUrl: String = ""
    var image: String = ""
    var episode: String = ""
    var url: String = ""
    var created: String = ""
}
