package pe.com.master.machines.domain.repositoryImpl.database

import pe.com.master.machines.data.repository.database.MovieLocalDataRepository
import pe.com.master.machines.domain.repository.database.GetSingleCharacterLocalUseCase
import javax.inject.Inject

class GetSingleCharacterLocalUseCaseImpl @Inject constructor(
    private val movieLocalDataRepository: MovieLocalDataRepository
) : GetSingleCharacterLocalUseCase {

    override fun invoke(id: Int) = movieLocalDataRepository.getSingleCharacter(id)

}