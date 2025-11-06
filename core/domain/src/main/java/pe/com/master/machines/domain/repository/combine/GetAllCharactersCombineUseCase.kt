package pe.com.master.machines.domain.repository.combine

import kotlinx.coroutines.flow.Flow
import pe.com.master.machines.common.response.Resource
import pe.com.master.machines.model.response.ResponseAllMovies

interface GetAllCharactersCombineUseCase {
    operator fun invoke(isHaveInternet: Boolean, page: Int): Flow<Resource<ResponseAllMovies>>
}
