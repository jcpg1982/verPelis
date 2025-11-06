package pe.com.master.machines.preferences.repositoryImpl

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import pe.com.master.machines.preferences.repository.PreferencesRepository
import pe.com.master.machines.preferences.utils.Constant.TOTAL_PAGES
import javax.inject.Inject

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class PreferencesRepositoryImpl @Inject constructor(
    private val context: Context
) : PreferencesRepository {

    override val totalPages: Flow<Int> = context.dataStore.data
        .map { preferences ->
            preferences[TOTAL_PAGES] ?: -1
        }

    override suspend fun setTotalPages(totalPages: Int) {
        context.dataStore.edit { settings ->
            settings[TOTAL_PAGES] = totalPages
        }
    }
}