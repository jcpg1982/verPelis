package pe.com.master.machines.domain.repository.combine

import kotlinx.coroutines.flow.Flow
import pe.com.master.machines.common.response.Resource
import pe.com.master.machines.model.response.ResponseAllMovies

interface GetSingleCharacterByNameCombineUseCase {

    operator fun invoke(
        isHaveInternet: Boolean, page: Int, name: String, status: String
    ): Flow<Resource<ResponseAllMovies>>
}
