package pe.com.master.machines.domain.remote

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
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
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import pe.com.master.machines.common.response.Resource
import pe.com.master.machines.common.response.toErrorType
import pe.com.master.machines.data.repository.database.MovieLocalDataRepository


@OptIn(ExperimentalCoroutinesApi::class)
class GetAllPetsUsesCaseTest {

    private val testDispatcher = StandardTestDispatcher()

    private val petsNetworkDataRepository: PetsNetworkDataRepository = mock()
    private val MovieLocalDataRepository: MovieLocalDataRepository = mock()

    private lateinit var useCase: GetAllPetsUsesCase

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        useCase = GetAllPetsUsesCase(petsNetworkDataRepository, MovieLocalDataRepository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `when local data is success and not empty, emits local data`() = runTest {
        val localPets = listOf(
            Pets(age = 1, description = "desc", name = "Pet1", image = "img1")
        )

        // Local success with data
        whenever(MovieLocalDataRepository.getAllPetsLocal())
            .thenReturn(flowOf(Resource.Success(localPets)))

        // No remote call expected, so we can just mock it but it shouldn't be called
        val fakeError = Throwable("Should not be called").toErrorType()

        val flowResponse: Flow<Resource<List<Pets>>> = flow {
            emit(Resource.Error(fakeError))
        }
        whenever(petsNetworkDataRepository.getLoadAllPets())
            .thenReturn(flowResponse)

        // Run use case
        val result = useCase.invoke().toList()

        // Advance coroutines
        advanceUntilIdle()

        // Result should contain only local Resource.Success with pets
        assertEquals(1, result.size)
        assertTrue(result[0] is Resource.Success)
        assertEquals(localPets, (result[0] as Resource.Success).data)
    }

    @Test
    fun `when local data is error, fetches remote and saves`() = runTest {
        val remotePets = listOf(
            Pets(age = 2, description = "desc2", name = "Pet2", image = "img2")
        )

        val fakeError = Throwable("local error").toErrorType()

        val flowResponse: Flow<Resource<List<Pets>>> = flow {
            emit(Resource.Error(fakeError))
        }
        whenever(MovieLocalDataRepository.getAllPetsLocal())
            .thenReturn(flowResponse)

        whenever(petsNetworkDataRepository.getLoadAllPets())
            .thenReturn(flowOf(Resource.Success(remotePets)))

        val flowResponseSave: Flow<Resource<Unit>> = flow {
            emit(Resource.Success(Unit))
        }
        whenever(MovieLocalDataRepository.savePets(remotePets))
            .thenReturn(flowResponseSave)

        val result = useCase.invoke().toList()

        advanceUntilIdle()

        assertEquals(1, result.size)
        assertTrue(result[0] is Resource.Success)
        assertEquals(remotePets, (result[0] as Resource.Success).data)

        // Verify that savePets was called with remotePets
        verify(MovieLocalDataRepository).savePets(remotePets)
    }

    @Test
    fun `when local data is success but empty, fetches remote and saves`() = runTest {
        val remotePets = listOf(
            Pets(age = 3, description = "desc3", name = "Pet3", image = "img3")
        )

        whenever(MovieLocalDataRepository.getAllPetsLocal())
            .thenReturn(flowOf(Resource.Success(emptyList())))

        whenever(petsNetworkDataRepository.getLoadAllPets())
            .thenReturn(flowOf(Resource.Success(remotePets)))

        val flowResponseSave: Flow<Resource<Unit>> = flow {
            emit(Resource.Success(Unit))
        }
        whenever(MovieLocalDataRepository.savePets(remotePets))
            .thenReturn(flowResponseSave)

        val result = useCase.invoke().toList()

        advanceUntilIdle()

        assertEquals(1, result.size)
        assertTrue(result[0] is Resource.Success)
        assertEquals(remotePets, (result[0] as Resource.Success).data)

        verify(MovieLocalDataRepository).savePets(remotePets)
    }
}
