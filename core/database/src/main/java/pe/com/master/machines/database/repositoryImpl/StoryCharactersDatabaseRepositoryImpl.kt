package pe.com.master.machines.database.repositoryImpl

import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import io.realm.kotlin.ext.query
import kotlinx.coroutines.flow.flow
import pe.com.master.machines.common.response.Resource
import pe.com.master.machines.common.response.toErrorType
import pe.com.master.machines.database.entity.MovieEntity
import pe.com.master.machines.database.repository.MoviesDatabaseRepository
import javax.inject.Inject


class MoviesDatabaseRepositoryImpl @Inject constructor(private val realmConfig: RealmConfiguration) :
    MoviesDatabaseRepository {

    private val TAG = MoviesDatabaseRepositoryImpl::class.java.simpleName

    private val realm: Realm
        get() = Realm.open(realmConfig)

    override fun saveAllCharacters(entities: List<MovieEntity>) = flow {
        try {
            realm.write {
                entities.forEach { copyToRealm(it) }
            }
            emit(Resource.Success(Unit))
        } catch (e: Exception) {
            emit(Resource.Error(e.toErrorType()))
        }
    }

    override fun getSingleCharacter(id: Int) = flow {
        try {
            val character = realm.query<MovieEntity>("id == $0", id).first().find()
            emit(Resource.Success(character))
        } catch (e: Exception) {
            emit(Resource.Error(e.toErrorType()))
        }
    }

    override fun getCharactersByPage(limit: Int, offset: Int) = flow {
        try {
            val characters = realm.query<MovieEntity>().sort("id")
                .find().drop(offset).take(limit)
            emit(Resource.Success(characters))
        } catch (e: Exception) {
            emit(Resource.Error(e.toErrorType()))
        }
    }

    override fun searchCharacterByName(
        limit: Int, offset: Int, query: String
    ) = flow {
        try {
            val characters = realm.query<MovieEntity>(
                "name CONTAINS[c] $0 AND status == $1", query,
            ).sort("id").find().drop(offset).take(limit)
            emit(Resource.Success(characters))
        } catch (e: Exception) {
            emit(Resource.Error(e.toErrorType()))
        }
    }
}