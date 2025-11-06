package pe.com.master.machines.firebase.repositoryImpl

import com.google.firebase.Firebase
import com.google.firebase.analytics.analytics
import com.google.firebase.analytics.logEvent
import kotlinx.coroutines.flow.flow
import pe.com.master.machines.common.response.Resource
import pe.com.master.machines.firebase.repository.FirebaseRepository
import javax.inject.Inject


class FirebaseRepositoryImpl @Inject constructor() : FirebaseRepository {

    private val analytics = Firebase.analytics

    override fun sendLogEventFirebase(
        event: String, movieId: Int, title: String, screen: String
    ) = flow {
        val result = analytics.logEvent(event) {
            param("movie_id", movieId.toLong())
            param("movie_title", title)
            param("screen_name", screen)
        }
        emit(Resource.Success(result))
    }

}