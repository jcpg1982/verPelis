package pe.com.master.machines.domain.repository.database

import kotlinx.coroutines.flow.Flow
import pe.com.master.machines.common.response.Resource
import pe.com.master.machines.model.model.Movie

interface GetAllCharactersLocalUseCase {
    operator fun invoke(limit: Int, offset: Int): Flow<Resource<List<Movie>>>
}
