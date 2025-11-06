package pe.com.master.machines.domain.repositoryImpl.firebase

import pe.com.master.machines.data.repository.firebase.FirebaseDataRepository
import pe.com.master.machines.domain.repository.firebase.SendLogEventAnalyticsUsesCase
import javax.inject.Inject

class SendLogEventAnalyticsUsesCaseImpl @Inject constructor(
    private var firebaseDataRepository: FirebaseDataRepository
) : SendLogEventAnalyticsUsesCase {

    override fun invoke(
        event: String, movieId: Int, title: String, screen: String
    ) = firebaseDataRepository.sendLogEventFirebase(event, movieId, title, screen)


}