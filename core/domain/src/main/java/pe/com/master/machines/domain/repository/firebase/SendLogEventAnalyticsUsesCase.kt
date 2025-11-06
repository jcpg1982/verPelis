package pe.com.master.machines.domain.repository.firebase

import kotlinx.coroutines.flow.Flow
import pe.com.master.machines.common.response.Resource

interface SendLogEventAnalyticsUsesCase {

    operator fun invoke(
        event: String, movieId: Int, title: String, screen: String
    ): Flow<Resource<Unit>>
}