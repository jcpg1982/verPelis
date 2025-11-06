package pe.com.master.machines.detail_character.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import pe.com.master.machines.design.components.dialogs.DialogConfirm
import pe.com.master.machines.design.components.dialogs.LoadingDialog
import pe.com.master.machines.design.components.images.ImageWithUrl
import pe.com.master.machines.design.components.text.CustomTwoText
import pe.com.master.machines.design.components.text.TextClickButton
import pe.com.master.machines.design.components.toolbar.ToolbarDefault
import pe.com.master.machines.design.theme.BlueGray300
import pe.com.master.machines.design.theme.ColorBlack
import pe.com.master.machines.design.theme.ContentInset
import pe.com.master.machines.design.theme.ContentInsetEight
import pe.com.master.machines.design.theme.ContentInsetThree
import pe.com.master.machines.design.theme.ContentInsetTwoHundredFifty
import pe.com.master.machines.design.theme.DynamicTextTwenty
import pe.com.master.machines.design.theme.Turquoise500
import pe.com.master.machines.design.utils.Utils.isConnected
import pe.com.master.machines.detail_character.viewmodel.DetailCharacterState
import pe.com.master.machines.detail_character.viewmodel.DetailCharacterViewmodel
import pe.com.master.machines.model.model.Movie

/*
@Composable
fun DetailCharacterScreen(
    characterId: Int,
    onNavigateToViewEpisodes: (String) -> Unit,
    onNavigateToBack: () -> Unit,
    detailCharacterViewmodel: DetailCharacterViewmodel = hiltViewModel()
) {
    val context = LocalContext.current
    val getMoviesState by detailCharacterViewmodel.getMoviesState.collectAsStateWithLifecycle()

    var title by remember { mutableStateOf("Cargando") }

    detailCharacterViewmodel.isConnected = context.isConnected

    LaunchedEffect(characterId) {
        detailCharacterViewmodel.getMovies(characterId)
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            ToolbarDefault(
                title = title,
                showIconBack = true,
                onClickBack = onNavigateToBack
            )
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier.padding(paddingValues)
        ) {
            when (val state = getMoviesState) {
                DetailCharacterState.First,
                DetailCharacterState.Loading -> LoadingDialog(message = "Cargando informacion del personaje")

                is DetailCharacterState.Error -> {
                    val title =
                        if (detailCharacterViewmodel.isConnected) "Error en el servidor" else "Sin internet"
                    DialogConfirm(
                        title = title,
                        message = state.throwable.message ?: "Error desconocido",
                        isCancelable = false,
                        textPositiveButton = "Reintentar",
                        textNegativeButton = "Salir",
                        onPositiveCallback = {
                            detailCharacterViewmodel.getMovies(characterId)
                        },
                        onNegativeCallback = {
                            onNavigateToBack()
                        }
                    )
                }

                is DetailCharacterState.Success -> {
                    title = state.data.name
                    DetailCharacterContent(
                        state.data,
                        onNavigateToViewEpisodes = onNavigateToViewEpisodes
                    )
                }
            }
        }
    }

}

@Composable
fun DetailCharacterContent(
    Movie: Movie,
    onNavigateToViewEpisodes: (String) -> Unit,
) {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .padding(horizontal = ContentInset)
            .fillMaxSize()
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        ImageWithUrl(
            photoUrl = Movie.image,
            modifier = Modifier
                .padding(all = ContentInsetEight)
                .background(Color.Transparent, CircleShape)
                .size(ContentInsetTwoHundredFifty)
                .clip(CircleShape)
                .border(ContentInsetThree, BlueGray300, CircleShape),
            contentScale = ContentScale.Crop,
        )

        Spacer(modifier = Modifier.height(ContentInsetEight))

        CustomTwoText(
            modifier = Modifier.fillMaxWidth(),
            title = Movie.name,
            subTitle = Movie.status,
            fontSize = DynamicTextTwenty,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(ContentInsetEight))

        CustomTwoText(
            modifier = Modifier.fillMaxWidth(),
            title = "Especie",
            subTitle = Movie.species
        )

        CustomTwoText(
            modifier = Modifier.fillMaxWidth(),
            title = "Genero",
            subTitle = Movie.gender
        )

        CustomTwoText(
            modifier = Modifier.fillMaxWidth(),
            title = "origen",
            subTitle = Movie.originName
        )

        CustomTwoText(
            modifier = Modifier.fillMaxWidth(),
            title = "Ubicacion",
            subTitle = Movie.locationName
        )

        Row {
            CustomTwoText(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                title = "Episodios",
                subTitle = "${Movie.episode.size} Episodios"
            )

            TextClickButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                textButton = "Ver todos",
                textColorButton = ColorBlack,
                backgroundColorButton = Turquoise500,
                onClick = {
                    val episodeIds = Movie.episode.map { url ->
                        url.substringAfterLast('/')
                    }
                    val idsString = episodeIds.joinToString(",")
                    onNavigateToViewEpisodes(idsString)
                }
            )
        }

    }
}

@Preview
@Composable
fun PreviewDetailCharacterContent() {
    DetailCharacterContent(
        Movie(
            name = "Joder",
            status = "Vivo",
            species = "Humano",
            gender = "Masculino",
            originName = "Tierra",
            locationName = "Tierra",
            image = ""
        ),
        onNavigateToViewEpisodes = {}
    )
}*/
