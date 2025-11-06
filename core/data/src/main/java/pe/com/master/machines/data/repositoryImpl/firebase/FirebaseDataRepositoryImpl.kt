package pe.com.master.machines.data.repositoryImpl.firebase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import pe.com.master.machines.common.response.Resource
import pe.com.master.machines.common.response.toErrorType
import pe.com.master.machines.data.mappers.asModel
import pe.com.master.machines.data.repository.firebase.FirebaseDataRepository
import pe.com.master.machines.firebase.repository.FirebaseRepository
import javax.inject.Inject


class FirebaseDataRepositoryImpl @Inject constructor(private val firebaseRepository: FirebaseRepository) :
    FirebaseDataRepository {


    override fun sendLogEventFirebase(
        event: String, movieId: Int, title: String, screen: String
    ) = firebaseRepository.sendLogEventFirebase(event, movieId, title, screen)
        .map { res ->
            when (res) {
                is Resource.Error<*> -> Resource.Error(res.error)
                is Resource.Success -> Resource.Success(res.data)
            }
        }
        .catch { emit(Resource.Error(it.toErrorType())) }

}