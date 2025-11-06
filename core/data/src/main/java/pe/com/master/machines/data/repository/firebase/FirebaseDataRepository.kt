package pe.com.master.machines.data.repository.firebase

import kotlinx.coroutines.flow.Flow
import pe.com.master.machines.common.response.Resource
import pe.com.master.machines.network.model.response.ResponseAllMoviesNetwork

interface FirebaseDataRepository {

    fun sendLogEventFirebase(
        event: String, movieId: Int, title: String, screen: String
    ): Flow<Resource<Unit>>
}
