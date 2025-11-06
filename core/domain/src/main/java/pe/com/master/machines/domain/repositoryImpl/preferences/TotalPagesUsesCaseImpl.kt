package pe.com.master.machines.domain.repositoryImpl.preferences

import pe.com.master.machines.data.repository.preferences.PreferencesDataRepository
import pe.com.master.machines.domain.repository.preferences.TotalPagesUsesCase
import javax.inject.Inject

class TotalPagesUsesCaseImpl @Inject constructor(
    private var preferencesDataRepository: PreferencesDataRepository
) : TotalPagesUsesCase {

    override fun invoke() = preferencesDataRepository.totalPages

    override suspend operator fun invoke(totalPages: Int) {
        preferencesDataRepository.setTotalPages(totalPages)
    }
}
