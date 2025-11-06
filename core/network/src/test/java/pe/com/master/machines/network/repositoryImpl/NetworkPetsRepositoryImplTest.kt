package pe.com.master.machines.network.repositoryImpl

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
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
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import pe.com.master.machines.common.response.Resource
import pe.com.master.machines.network.api.ApiService


@OptIn(ExperimentalCoroutinesApi::class)
class NetworkPetsRepositoryImplTest {

    private val testDispatcher = StandardTestDispatcher()

    private val apiService: ApiService = mock()
    private lateinit var repository: MovieNetworkRepositoryImpl

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        repository = MovieNetworkRepositoryImpl(apiService)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `getLoadAllPets emits success when api returns data`() = runTest {
        val fakePetsNetwork = listOf(
            PetsNetwork(),
            PetsNetwork()
        )

        whenever(apiService.getLoadAllCategories()).thenReturn(fakePetsNetwork)

        val results = repository.getLoadAllPets().toList()

        advanceUntilIdle()

        assertEquals(1, results.size)
        assertTrue(results[0] is Resource.Success)
        assertEquals(fakePetsNetwork, (results[0] as Resource.Success).data)
    }

}
