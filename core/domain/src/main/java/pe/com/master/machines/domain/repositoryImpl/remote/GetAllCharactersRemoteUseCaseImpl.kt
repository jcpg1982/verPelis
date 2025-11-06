package pe.com.master.machines.domain.repositoryImpl.remote

import pe.com.master.machines.data.repository.remote.MovieRemoteDataRepository
import pe.com.master.machines.domain.repository.remote.GetAllCharactersRemoteUseCase
import javax.inject.Inject

class GetAllCharactersRemoteUseCaseImpl @Inject constructor(
    private val movieRemoteDataRepository: MovieRemoteDataRepository
) : GetAllCharactersRemoteUseCase {

    override fun invoke(page: Int) = movieRemoteDataRepository.getLoadAllCharacters(page)
}