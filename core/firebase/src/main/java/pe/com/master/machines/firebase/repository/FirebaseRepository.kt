package pe.com.master.machines.firebase.repository

import kotlinx.coroutines.flow.Flow
import pe.com.master.machines.common.response.Resource

interface FirebaseRepository {
    fun sendLogEventFirebase(
        event: String, movieId: Int, title: String, screen: String
    ): Flow<Resource<Unit>>
}
