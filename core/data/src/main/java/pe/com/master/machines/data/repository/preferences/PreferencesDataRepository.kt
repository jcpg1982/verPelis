package pe.com.master.machines.data.repository.preferences

import kotlinx.coroutines.flow.Flow

interface PreferencesDataRepository {

    val totalPages: Flow<Int>
    suspend fun setTotalPages(totalPages: Int)
}