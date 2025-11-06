package pe.com.master.machines.data.repositoryImpl.local.database

import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import pe.com.master.machines.common.response.Resource
import pe.com.master.machines.common.response.toErrorType
import pe.com.master.machines.data.repository.database.MovieLocalDataRepository
import pe.com.master.machines.database.repository.MoviesDatabaseRepository
import pe.com.master.machines.model.model.Movie
import javax.inject.Inject

class MovieLocalDataRepositoryImpl @Inject constructor(private val moviesDatabaseRepository: MoviesDatabaseRepository) :
    MovieLocalDataRepository {

    override fun saveAllCharacters(entities: List<Movie>) =
        moviesDatabaseRepository.saveAllCharacters(entities.toEntity()).map { res ->
            when (res) {
                is Resource.Error -> Resource.Error(res.error)
                is Resource.Success -> Resource.Success(res.data)
            }
        }.catch { emit(Resource.Error(it.toErrorType())) }

    override fun getSingleCharacter(id: Int) =
        moviesDatabaseRepository.getSingleCharacter(id).map { res ->
            when (res) {
                is Resource.Error -> Resource.Error(res.error)
                is Resource.Success -> Resource.Success(res.data.asModel())
            }
        }.catch { emit(Resource.Error(it.toErrorType())) }

    override fun getCharactersByPage(
        limit: Int, offset: Int
    ) = moviesDatabaseRepository.getCharactersByPage(limit, offset).map { res ->
        when (res) {
            is Resource.Error -> Resource.Error(res.error)
            is Resource.Success -> Resource.Success(res.data.asModel())
        }
    }.catch { emit(Resource.Error(it.toErrorType())) }

    override fun searchCharacterByName(
        limit: Int, offset: Int, name: String, status: String
    ) = moviesDatabaseRepository.searchCharacterByName(limit, offset, name, status)
        .map { res ->
            when (res) {
                is Resource.Error -> Resource.Error(res.error)
                is Resource.Success -> Resource.Success(res.data.asModel())
            }
        }.catch { emit(Resource.Error(it.toErrorType())) }
}