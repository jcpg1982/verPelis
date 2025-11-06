package pe.com.master.machines.view_episodes.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import pe.com.master.machines.design.components.dialogs.DialogConfirm
import pe.com.master.machines.design.components.dialogs.LoadingDialog
import pe.com.master.machines.design.components.toolbar.ToolbarDefault
import pe.com.master.machines.design.utils.Utils.isConnected
import pe.com.master.machines.view_episodes.viewmodel.ViewEpisodesState
import pe.com.master.machines.view_episodes.viewmodel.ViewEpisodesViewmodel

@Composable
fun ViewEpisodesScreen(
    ids: String,
    onNavigateToBack: () -> Unit,
    viewEpisodesViewmodel: ViewEpisodesViewmodel = hiltViewModel()
) {
    val context = LocalContext.current
    val getMoviesState by viewEpisodesViewmodel.getMoviesState.collectAsStateWithLifecycle()

    viewEpisodesViewmodel.isConnected = context.isConnected

    LaunchedEffect(ids) {
        viewEpisodesViewmodel.getObtainEpisodes(ids)
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            ToolbarDefault(
                title = "Listado de episodios",
                showIconBack = true,
                onClickBack = onNavigateToBack
            )
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier.padding(paddingValues)
        ) {
            when (val state = getMoviesState) {
                ViewEpisodesState.First,
                ViewEpisodesState.Loading -> LoadingDialog(message = "Cargando informacion de los episodios")

                is ViewEpisodesState.Error -> {
                    val title =
                        if (viewEpisodesViewmodel.isConnected) "Error en el servidor" else "Sin internet"
                    DialogConfirm(
                        title = title,
                        message = state.throwable.message ?: "Error desconocido",
                        isCancelable = false,
                        textPositiveButton = "Reintentar",
                        textNegativeButton = "Salir",
                        onPositiveCallback = {
                            viewEpisodesViewmodel.getObtainEpisodes(ids)
                        },
                        onNegativeCallback = {
                            onNavigateToBack()
                        }
                    )
                }

                /*is ViewEpisodesState.Success -> {
                    LazyColumn(
                        modifier = Modifier
                            .padding(horizontal = ContentInset)
                            .weight(1f),
                    ) {
                        items(state.dataList) { item ->
                            EpisodeRow(item = item)
                            Spacer(modifier = Modifier.padding(bottom = ContentInsetEight))
                        }
                    }
                }*/
            }
        }
    }

}