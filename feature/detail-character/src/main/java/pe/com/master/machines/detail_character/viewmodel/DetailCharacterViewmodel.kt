package pe.com.master.machines.detail_character.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import pe.com.master.machines.common.response.Resource
import pe.com.master.machines.common.utils.messageError
import pe.com.master.machines.domain.repository.combine.GetSingleMovieCombineUseCase
import pe.com.master.machines.domain.repository.firebase.SendLogEventAnalyticsUsesCase
import javax.inject.Inject

@HiltViewModel
class DetailCharacterViewmodel @Inject constructor(
    private val getSingleMovieCombineUseCase: GetSingleMovieCombineUseCase,
    private val sendLogEventAnalyticsUsesCase: SendLogEventAnalyticsUsesCase,
) : ViewModel() {
    private val TAG = DetailCharacterViewmodel::class.java.simpleName

    private val _getMoviesState: MutableStateFlow<DetailCharacterState> =
        MutableStateFlow(DetailCharacterState.First)
    val getMoviesState = _getMoviesState.asStateFlow()

    var isConnected = false

    fun getMovies(characterId: Int) {
        viewModelScope.launch {
            getSingleMovieCombineUseCase.invoke(isConnected, characterId)
                .flowOn(Dispatchers.IO)
                .onStart {
                    Log.i(TAG, "getMovies onStart")
                    _getMoviesState.update { DetailCharacterState.Loading }
                }
                .catch { e ->
                    Log.i(TAG, "getMovies catch ${e.message}")
                    _getMoviesState.update { DetailCharacterState.Error(e) }
                }
                .collect { res ->
                    when (res) {
                        is Resource.Error -> {
                            Log.i(TAG, "getMovies collect error ${res.messageError}")
                            _getMoviesState.update { DetailCharacterState.Error(Throwable(res.messageError)) }
                        }

                        is Resource.Success -> {
                            Log.i(TAG, "getMovies collect Success ${res.data}")
                            sendLogEvent(
                                "view_movie_detail",
                                res.data?.id ?: -1,
                                res.data?.title.orEmpty(),
                                "view_movie_detail"
                            )
                            _getMoviesState.update { DetailCharacterState.Success(res.data) }
                        }
                    }
                }
        }
    }

    private fun sendLogEvent(
        event: String, movieId: Int, title: String, screen: String
    ) {
        viewModelScope.launch {
            sendLogEventAnalyticsUsesCase.invoke(event, movieId, title, screen)
                .flowOn(Dispatchers.IO)
                .onStart {
                    Log.i(TAG, "sendLogEvent onStart")
                }
                .catch { e ->
                    Log.i(TAG, "sendLogEvent catch ${e.message}")
                }
                .collect { res ->
                    when (res) {
                        is Resource.Error -> {
                            Log.i(TAG, "sendLogEvent collect error ${res.messageError}")
                        }

                        is Resource.Success -> {
                            Log.i(TAG, "sendLogEvent collect Success ${res.data}")
                        }
                    }
                }
        }
    }
}