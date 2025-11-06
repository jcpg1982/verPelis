package pe.com.master.machines.view_episodes.viewmodel

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
import javax.inject.Inject

@HiltViewModel
class ViewEpisodesViewmodel @Inject constructor(
    //private val getEpisodesByIdsRemoteUseCase: GetEpisodesByIdsRemoteUseCase,
) : ViewModel() {
    private val TAG = ViewEpisodesViewmodel::class.java.simpleName

    private val _getMoviesState: MutableStateFlow<ViewEpisodesState> =
        MutableStateFlow(ViewEpisodesState.First)
    val getMoviesState = _getMoviesState.asStateFlow()

    var isConnected = false

    fun getObtainEpisodes(ids: String) {
        viewModelScope.launch {
            /*getEpisodesByIdsRemoteUseCase.invoke(ids)
                .flowOn(Dispatchers.IO)
                .onStart {
                    Log.i(TAG, "getObtainEpisodes onStart")
                    _getMoviesState.update { ViewEpisodesState.Loading }
                }
                .catch { e ->
                    Log.i(TAG, "getObtainEpisodes catch ${e.message}")
                    _getMoviesState.update { ViewEpisodesState.Error(e) }
                }
                .collect { res ->
                    when (res) {
                        is Resource.Error -> {
                            Log.i(TAG, "getObtainEpisodes collect error ${res.messageError}")
                            _getMoviesState.update { ViewEpisodesState.Error(Throwable(res.messageError)) }
                        }

                        is Resource.Success -> {
                            Log.i(TAG, "getObtainEpisodes collect Success ${res.data}")
                            _getMoviesState.update { ViewEpisodesState.Success(res.data) }
                        }
                    }
                }*/
        }
    }
}