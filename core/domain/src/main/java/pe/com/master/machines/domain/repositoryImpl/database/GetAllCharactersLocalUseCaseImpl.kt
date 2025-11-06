package pe.com.master.machines.domain.repositoryImpl.database

import pe.com.master.machines.data.repository.database.MovieLocalDataRepository
import pe.com.master.machines.domain.repository.database.GetAllCharactersLocalUseCase
import javax.inject.Inject

class GetAllCharactersLocalUseCaseImpl @Inject constructor(
    private val movieLocalDataRepository: MovieLocalDataRepository
) : GetAllCharactersLocalUseCase {

    override fun invoke(limit: Int, offset: Int) =
        movieLocalDataRepository.getCharactersByPage(limit, offset)
}