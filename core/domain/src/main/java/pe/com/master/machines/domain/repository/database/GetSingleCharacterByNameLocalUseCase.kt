package pe.com.master.machines.domain.repository.database

import kotlinx.coroutines.flow.Flow
import pe.com.master.machines.common.response.Resource
import pe.com.master.machines.model.model.Movie

interface GetSingleCharacterByNameLocalUseCase {
    operator fun invoke(
        limit: Int, offset: Int, query: String
    ): Flow<Resource<List<Movie>>>
}
