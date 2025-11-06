package pe.com.master.machines.view_episodes.viewmodel

sealed interface ViewEpisodesState {

    data object First : ViewEpisodesState
    data object Loading : ViewEpisodesState
    data class Error(val throwable: Throwable) : ViewEpisodesState
    //data class Success(val dataList: List<Episode>) : ViewEpisodesState
}