package pe.com.master.machines.domain.repository.remote

import kotlinx.coroutines.flow.Flow
import pe.com.master.machines.common.response.Resource
import pe.com.master.machines.model.response.ResponseAllMovies

interface GetSingleCharacterByNameRemoteUseCase {

    operator fun invoke(
        page: Int, name: String, status: String
    ): Flow<Resource<ResponseAllMovies>>
}
