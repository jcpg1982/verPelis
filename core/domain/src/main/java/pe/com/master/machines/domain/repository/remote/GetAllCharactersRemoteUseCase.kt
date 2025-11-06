package pe.com.master.machines.domain.repository.remote

import kotlinx.coroutines.flow.Flow
import pe.com.master.machines.common.response.Resource
import pe.com.master.machines.model.response.ResponseAllMovies

interface GetAllCharactersRemoteUseCase {

    operator fun invoke(page: Int): Flow<Resource<ResponseAllMovies>>
}
