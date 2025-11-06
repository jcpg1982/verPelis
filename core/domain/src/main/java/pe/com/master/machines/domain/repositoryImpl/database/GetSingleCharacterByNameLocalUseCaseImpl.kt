package pe.com.master.machines.domain.repositoryImpl.database

import pe.com.master.machines.data.repository.database.MovieLocalDataRepository
import pe.com.master.machines.domain.repository.database.GetSingleCharacterByNameLocalUseCase
import javax.inject.Inject

class GetSingleCharacterByNameLocalUseCaseImpl @Inject constructor(
    private val movieLocalDataRepository: MovieLocalDataRepository
) : GetSingleCharacterByNameLocalUseCase {

    override fun invoke(
        limit: Int, offset: Int, name: String, status: String
    ) = movieLocalDataRepository.searchCharacterByName(limit, offset, name, status)

}