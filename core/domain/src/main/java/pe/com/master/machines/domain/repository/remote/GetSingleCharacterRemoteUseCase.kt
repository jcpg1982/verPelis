package pe.com.master.machines.domain.repository.remote

import kotlinx.coroutines.flow.Flow
import pe.com.master.machines.common.response.Resource
import pe.com.master.machines.model.model.Movie

interface GetSingleCharacterRemoteUseCase {

    operator fun invoke(id: Int): Flow<Resource<Movie?>>
}
