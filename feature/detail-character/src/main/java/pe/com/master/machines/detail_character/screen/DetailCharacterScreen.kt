package pe.com.master.machines.detail_character.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absoluteOffset
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
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import pe.com.master.machines.design.components.dialogs.DialogConfirm
import pe.com.master.machines.design.components.dialogs.LoadingDialog
import pe.com.master.machines.design.components.images.ImageWithUrl
import pe.com.master.machines.design.components.text.CustomText
import pe.com.master.machines.design.components.text.CustomTwoText
import pe.com.master.machines.design.components.toolbar.ToolbarDefault
import pe.com.master.machines.design.theme.BlueGray300
import pe.com.master.machines.design.theme.BlueGray700
import pe.com.master.machines.design.theme.ColorWhite
import pe.com.master.machines.design.theme.ContentInset
import pe.com.master.machines.design.theme.ContentInsetEight
import pe.com.master.machines.design.theme.ContentInsetOneHundredFifty
import pe.com.master.machines.design.theme.ContentInsetThree
import pe.com.master.machines.design.theme.ContentInsetTwoHundredFifty
import pe.com.master.machines.design.theme.DynamicTextTwenty
import pe.com.master.machines.design.utils.Utils.isConnected
import pe.com.master.machines.detail_character.viewmodel.DetailCharacterState
import pe.com.master.machines.detail_character.viewmodel.DetailCharacterViewmodel
import pe.com.master.machines.model.model.Movie

@Composable
fun DetailCharacterScreen(
    characterId: Int,
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
                    state.data?.let {
                        title = it.title
                        DetailCharacterContent(it)
                    }
                }
            }
        }
    }

}

@Composable
fun DetailCharacterContent(movie: Movie) {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box(
            modifier = Modifier
                .absoluteOffset()
                .height(ContentInsetTwoHundredFifty),
            contentAlignment = Alignment.CenterStart
        ) {

            ImageWithUrl(
                photoUrl = "https://image.tmdb.org/t/p/w1280${movie.backdropPath}",
                modifier = Modifier
                    .fillMaxSize(),
                contentScale = ContentScale.Crop,
            )

            Spacer(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = .4f))
            )

            Row(
                modifier = Modifier
                    .padding(all = ContentInset)
                    .fillMaxSize(),
                horizontalArrangement = Arrangement.Start
            ) {
                ImageWithUrl(
                    photoUrl = "https://image.tmdb.org/t/p/w500${movie.posterPath}",
                    modifier = Modifier
                        .padding(all = ContentInsetEight)
                        .background(Color.Transparent, CircleShape)
                        .size(ContentInsetOneHundredFifty)
                        .clip(CircleShape)
                        .border(ContentInsetThree, BlueGray300, CircleShape),
                    contentScale = ContentScale.Crop,
                )

                Column(modifier = Modifier.weight(1f)) {

                    CustomTwoText(
                        modifier = Modifier.fillMaxWidth(),
                        title = movie.title,
                        subTitle = movie.originalTitle,
                        fontSize = DynamicTextTwenty,
                        textAlign = TextAlign.Start
                    )

                    CustomTwoText(
                        modifier = Modifier.fillMaxWidth(),
                        title = "${movie.voteAverage}",
                        subTitle = "Total de votos (${movie.voteCount})",
                        fontSize = DynamicTextTwenty,
                        textAlign = TextAlign.Start
                    )

                    CustomText(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        text = movie.releaseDate,
                        textAlign = TextAlign.Start,
                        textColor = ColorWhite,
                        maxLines = 1
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(ContentInsetEight))

        CustomText(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = ContentInset),
            text = movie.overview,
            textAlign = TextAlign.Justify,
            textColor = BlueGray700,
            maxLines = 50
        )

        Spacer(modifier = Modifier.height(ContentInsetEight))

    }
}

@Preview
@Composable
fun PreviewDetailCharacterContent() {
    DetailCharacterContent(Movie())
}
