package pe.com.master.machines.domain.repositoryImpl.database

import pe.com.master.machines.data.repository.database.MovieLocalDataRepository
import pe.com.master.machines.domain.repository.database.SaveAllCharactersLocalUseCase
import pe.com.master.machines.model.model.Movie
import javax.inject.Inject

class SaveAllCharactersLocalUseCaseImpl @Inject constructor(
    private val movieLocalDataRepository: MovieLocalDataRepository
) : SaveAllCharactersLocalUseCase {

    override fun invoke(entities: List<Movie>) =
        movieLocalDataRepository.saveAllCharacters(entities)

}