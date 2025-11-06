package pe.com.master.machines.domain.repository.preferences

import kotlinx.coroutines.flow.Flow

interface TotalPagesUsesCase {

    operator fun invoke(): Flow<Int>
    suspend operator fun invoke(totalPages: Int)
}