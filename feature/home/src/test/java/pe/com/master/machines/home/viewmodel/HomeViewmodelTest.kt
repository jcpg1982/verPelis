package pe.com.master.machines.home.viewmodel

import app.cash.turbine.test
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever
import pe.com.master.machines.common.response.Resource
import pe.com.master.machines.common.response.toErrorType
import pe.com.master.machines.domain.remote.GetAllPetsUsesCase

class HomeViewmodelTest {

    private val testDispatcher = StandardTestDispatcher()
    private lateinit var viewModel: HomeViewmodel
    private val getAllPetsUseCase: GetAllPetsUsesCase = mock()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `when use case emits success, viewmodel updates state to Success`() = runTest {
        val fakePets = listOf(
            Pets(age = 1, description = "desc1", name = "Pet1", image = "img1"),
            Pets(age = 2, description = "desc2", name = "Pet2", image = "img2"),
        )

        val flowResponse: Flow<Resource<List<Pets>>> = flow {
            emit(Resource.Success(fakePets))
        }

        whenever(getAllPetsUseCase.invoke()).thenReturn(flowResponse)

        viewModel = HomeViewmodel(getAllPetsUseCase)

        // Avanza corrutinas para emitir los valores
        advanceUntilIdle()

        viewModel.getAllCharactersState.test {
            // Primero debe emitirse Loading
            val loadingState = awaitItem()
            assertTrue(loadingState is HomeState.Loading)

            // Luego el estado Success con la lista fakePets
            val successState = awaitItem()
            assertTrue(successState is HomeState.SuccessAllCharacter)
            val pets = (successState as HomeState.SuccessAllCharacter).dataList
            assertEquals(fakePets, pets)

            cancelAndIgnoreRemainingEvents()
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `when use case emits error, viewmodel updates state to Error`() = runTest {
        val fakeError = Throwable("Error fetching pets").toErrorType()

        val flowResponse: Flow<Resource<List<Pets>>> = flow {
            emit(Resource.Error(fakeError))
        }

        whenever(getAllPetsUseCase.invoke()).thenReturn(flowResponse)

        viewModel = HomeViewmodel(getAllPetsUseCase)

        advanceUntilIdle()

        viewModel.getAllCharactersState.test {
            // Loading inicial
            assertTrue(awaitItem() is HomeState.Loading)

            // Estado Error
            val errorState = awaitItem()
            assertTrue(errorState is HomeState.Error)
            val error = (errorState as HomeState.Error).throwable.toErrorType()
            assertEquals(fakeError, error)

            cancelAndIgnoreRemainingEvents()
        }
    }
}