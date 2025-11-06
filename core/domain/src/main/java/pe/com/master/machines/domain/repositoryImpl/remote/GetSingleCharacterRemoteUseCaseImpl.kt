package pe.com.master.machines.domain.repositoryImpl.remote

import pe.com.master.machines.data.repository.remote.MovieRemoteDataRepository
import pe.com.master.machines.domain.repository.remote.GetSingleCharacterRemoteUseCase
import javax.inject.Inject

class GetSingleCharacterRemoteUseCaseImpl @Inject constructor(
    private val movieRemoteDataRepository: MovieRemoteDataRepository
) : GetSingleCharacterRemoteUseCase {

    override fun invoke(id: Int) = movieRemoteDataRepository.getLoadSingleCharacter(id)

}