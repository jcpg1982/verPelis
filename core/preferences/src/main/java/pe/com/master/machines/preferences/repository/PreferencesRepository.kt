package pe.com.master.machines.preferences.repository

import kotlinx.coroutines.flow.Flow

interface PreferencesRepository {
    val totalPages: Flow<Int>
    suspend fun setTotalPages(totalPages: Int)
}
