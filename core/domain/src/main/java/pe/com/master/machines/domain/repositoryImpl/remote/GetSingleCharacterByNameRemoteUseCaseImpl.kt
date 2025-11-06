package pe.com.master.machines.domain.repositoryImpl.remote

import pe.com.master.machines.data.repository.remote.MovieRemoteDataRepository
import pe.com.master.machines.domain.repository.remote.GetSingleCharacterByNameRemoteUseCase
import javax.inject.Inject

class GetSingleCharacterByNameRemoteUseCaseImpl @Inject constructor(
    private val movieRemoteDataRepository: MovieRemoteDataRepository
) : GetSingleCharacterByNameRemoteUseCase {

    override fun invoke(
        page: Int, name: String, status: String
    ) = movieRemoteDataRepository.searchCharacterByName(page, name, status)
}