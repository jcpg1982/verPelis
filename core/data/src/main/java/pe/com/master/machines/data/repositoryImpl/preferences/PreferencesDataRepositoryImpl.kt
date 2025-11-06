package pe.com.master.machines.data.repositoryImpl.preferences

import pe.com.master.machines.data.repository.preferences.PreferencesDataRepository
import pe.com.master.machines.preferences.repository.PreferencesRepository
import javax.inject.Inject

class PreferencesDataRepositoryImpl @Inject constructor(
    private val preferencesRepository: PreferencesRepository
) : PreferencesDataRepository {

    override val totalPages = preferencesRepository.totalPages

    override suspend fun setTotalPages(totalPages: Int) {
        preferencesRepository.setTotalPages(totalPages)
    }
}