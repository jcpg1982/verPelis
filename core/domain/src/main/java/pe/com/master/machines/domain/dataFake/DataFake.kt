package pe.com.master.machines.domain.dataFake

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import pe.com.master.machines.common.response.Resource
import pe.com.master.machines.domain.repository.combine.GetAllCharactersCombineUseCase
import pe.com.master.machines.domain.repository.combine.GetSingleCharacterByNameCombineUseCase
import pe.com.master.machines.domain.repository.preferences.TotalPagesUsesCase
import pe.com.master.machines.model.response.ResponseAllMovies

object DataFake {
    val fakeTotalPagesUsesCase = object : TotalPagesUsesCase {
        override fun invoke(): Flow<Int> {
            return flowOf()
        }

        override suspend fun invoke(totalPages: Int) {
        }
    }
    val fakeGetAllCharactersCombineUseCase = object : GetAllCharactersCombineUseCase {
        override fun invoke(
            isHaveInternet: Boolean, page: Int
        ): Flow<Resource<ResponseAllMovies>> {
            return flowOf()
        }

    }
    val fakeGetSingleCharacterByNameCombineUseCase =
        object : GetSingleCharacterByNameCombineUseCase {
            override fun invoke(
                isHaveInternet: Boolean, page: Int, name: String, status: String
            ): Flow<Resource<ResponseAllMovies>> {
                return flowOf()
            }

        }
}