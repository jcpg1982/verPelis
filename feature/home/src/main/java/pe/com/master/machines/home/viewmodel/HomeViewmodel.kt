package pe.com.master.machines.home.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import pe.com.master.machines.common.response.Resource
import pe.com.master.machines.common.utils.messageError
import pe.com.master.machines.domain.repository.combine.GetAllCharactersCombineUseCase
import pe.com.master.machines.domain.repository.combine.GetSingleCharacterByNameCombineUseCase
import pe.com.master.machines.domain.repository.preferences.TotalPagesUsesCase
import pe.com.master.machines.model.model.Movie
import javax.inject.Inject

@HiltViewModel
class HomeViewmodel @Inject constructor(
    private val totalPagesUsesCase: TotalPagesUsesCase,
    private val getAllCharactersCombineUseCase: GetAllCharactersCombineUseCase,
    private val getSingleCharacterByNameCombineUseCase: GetSingleCharacterByNameCombineUseCase,
) : ViewModel() {
    private val TAG = HomeViewmodel::class.java.simpleName

    val totalPages = totalPagesUsesCase.invoke().stateIn(
        scope = viewModelScope, started = SharingStarted.Eagerly, initialValue = 1
    )
    private val _getAllCharactersState: MutableStateFlow<HomeState> =
        MutableStateFlow(HomeState.First)
    val getAllCharactersState = _getAllCharactersState.asStateFlow()
    private val _searchCharacterState = MutableStateFlow("")
    val searchCharacterState = _searchCharacterState.asStateFlow()
    private val _listCharacters = MutableStateFlow(listOf<Movie>())
    val listCharacters = _listCharacters.asStateFlow()
    var pageCurrent = 1
    var isConnected = false

    init {
        getAllCharacters()
    }

    fun updateSearchCharacter(search: String) {
        updateListCharacter()
        pageCurrent = 1
        _searchCharacterState.update { search }
    }

    fun updateListCharacter() {
        _listCharacters.update { listOf() }
    }

    fun getAllCharacters() {
        viewModelScope.launch {
            getAllCharactersCombineUseCase.invoke(isConnected, pageCurrent)
                .flowOn(Dispatchers.IO)
                .onStart {
                    Log.i(TAG, "getAllCharacters onStart")
                    _getAllCharactersState.update { HomeState.Loading }
                }
                .catch { e ->
                    Log.i(TAG, "getAllCharacters catch ${e.message}")
                    _getAllCharactersState.update { HomeState.Error(e) }
                }
                .collect { res ->
                    when (res) {
                        is Resource.Error -> {
                            Log.i(TAG, "getAllCharacters collect error ${res.messageError}")
                            _getAllCharactersState.update { HomeState.Error(Throwable(res.messageError)) }
                        }

                        is Resource.Success -> {
                            Log.i(TAG, "getAllCharacters collect Success ${res.data}")
                            updateListCharacters(res.data.results)
                            _getAllCharactersState.update { HomeState.SuccessAllCharacter(res.data) }
                        }
                    }
                }
        }
    }

    fun getSingleCharacterByName() {
        viewModelScope.launch {
            getSingleCharacterByNameCombineUseCase.invoke(
                isConnected,
                pageCurrent,
                searchCharacterState.value,
                "alive"
            )
                .flowOn(Dispatchers.IO)
                .onStart {
                    Log.i(TAG, "getSingleCharacterByName onStart")
                    _getAllCharactersState.update { HomeState.Loading }
                }
                .catch { e ->
                    Log.i(TAG, "getSingleCharacterByName catch ${e.message}")
                    _getAllCharactersState.update { HomeState.Error(e) }
                }
                .collect { res ->
                    when (res) {
                        is Resource.Error -> {
                            Log.i(TAG, "getSingleCharacterByName collect error ${res.messageError}")
                            _getAllCharactersState.update { HomeState.Error(Throwable(res.messageError)) }
                        }

                        is Resource.Success -> {
                            Log.i(TAG, "getSingleCharacterByName collect Success ${res.data}")
                            updateListCharacters(res.data.results)
                            _getAllCharactersState.update {
                                HomeState.SuccessSingleCharacterByName(res.data)
                            }
                        }
                    }
                }
        }
    }

    fun paginationCharacters() {
        if (getAllCharactersState.value is HomeState.Loading) return
        if (pageCurrent >= totalPages.value) return
        pageCurrent++
        if (searchCharacterState.value.isNotBlank()) {
            getSingleCharacterByName()
        } else {
            getAllCharacters()
        }
    }

    private fun updateListCharacters(list: List<Movie>) {
        _listCharacters.update { currentList ->
            val existingIds = currentList.map { it.id }.toSet()
            val newCharacters = list.filter { it.id !in existingIds }
            currentList + newCharacters
        }
    }
}