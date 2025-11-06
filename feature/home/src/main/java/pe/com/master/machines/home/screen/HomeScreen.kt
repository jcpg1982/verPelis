package pe.com.master.machines.home.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.delay
import pe.com.master.machines.design.components.dialogs.DialogAlert
import pe.com.master.machines.design.components.dialogs.LoadingDialog
import pe.com.master.machines.design.components.text.CustomText
import pe.com.master.machines.design.components.text.SearchText
import pe.com.master.machines.design.theme.ColorBlack
import pe.com.master.machines.design.theme.ContentInset
import pe.com.master.machines.design.theme.ContentInsetEight
import pe.com.master.machines.design.theme.DynamicTextEight
import pe.com.master.machines.design.utils.Utils.isConnected
import pe.com.master.machines.domain.dataFake.DataFake.fakeGetAllCharactersCombineUseCase
import pe.com.master.machines.domain.dataFake.DataFake.fakeGetSingleCharacterByNameCombineUseCase
import pe.com.master.machines.domain.dataFake.DataFake.fakeTotalPagesUsesCase
import pe.com.master.machines.home.viewmodel.HomeState
import pe.com.master.machines.home.viewmodel.HomeViewmodel

@Composable
fun HomeScreen(
    onNavigateToViewEpisodes: (String) -> Unit,
    onNavigateToDetailCharacter: (Int) -> Unit,
    homeViewmodel: HomeViewmodel = hiltViewModel()
) {
    val context = LocalContext.current
    val getAllCharactersState by homeViewmodel.getAllCharactersState.collectAsStateWithLifecycle()
    val searchCharacterState by homeViewmodel.searchCharacterState.collectAsStateWithLifecycle()
    val listCharacters by homeViewmodel.listCharacters.collectAsStateWithLifecycle()
    var idCharacterSelected by remember { mutableStateOf<Int?>(null) }

    var isLoading by remember { mutableStateOf(false) }
    var messageError by remember { mutableStateOf("") }
    val listState = rememberLazyListState()
    homeViewmodel.isConnected = context.isConnected

    LaunchedEffect(getAllCharactersState) {
        when (val state = getAllCharactersState) {
            HomeState.First,
            HomeState.Loading -> {
                messageError = ""
                isLoading = true
            }

            is HomeState.Error -> {
                isLoading = false
                messageError = state.throwable.message ?: "Error desconocido"
            }

            is HomeState.SuccessAllCharacter -> {
                messageError = ""
                isLoading = false
            }

            is HomeState.SuccessSingleCharacterByName -> {
                messageError = ""
                isLoading = false
            }
        }
    }
    LaunchedEffect(searchCharacterState) {
        if (searchCharacterState.isNotBlank()) {
            delay(1000)
            homeViewmodel.getSingleCharacterByName()
        } else {
            homeViewmodel.pageCurrent = 1
            homeViewmodel.updateListCharacter()
            homeViewmodel.getAllCharacters()
        }
    }

    val shouldLoadMore by remember {
        derivedStateOf {
            val lastVisibleItem = listState.layoutInfo.visibleItemsInfo.lastOrNull()
            lastVisibleItem != null && lastVisibleItem.index == listCharacters.size - 1
        }
    }

    LaunchedEffect(shouldLoadMore) {
        if (shouldLoadMore && searchCharacterState.isBlank()) {
            homeViewmodel.paginationCharacters()
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { paddingValues ->

        Column(
            modifier = Modifier.padding(paddingValues)
        ) {
            val textInternet = if (homeViewmodel.isConnected) "Conectado" else "Sin internet"
            val colorBackground = if (homeViewmodel.isConnected) Color.Green else Color.Red
            CustomText(
                modifier = Modifier
                    .padding(horizontal = ContentInset)
                    .fillMaxWidth()
                    .background(colorBackground, shape = RoundedCornerShape(ContentInsetEight)),
                text = textInternet,
                fontSize = DynamicTextEight,
                textColor = ColorBlack,
                textAlign = TextAlign.Center
            )

            SearchText(
                hintSearch = "Busca tu personaje favorito",
                maxCharacter = 50,
                modifier = Modifier
                    .padding(horizontal = ContentInset),
                onMessageSearch = {
                    homeViewmodel.updateSearchCharacter(it)
                }
            )

            Spacer(modifier = Modifier.padding(ContentInsetEight))

            LazyColumn(
                modifier = Modifier.weight(1f),
                state = listState
            ) {
                /*items(listCharacters) { item ->
                    HomeCharacterRow(
                        isExpanded = idCharacterSelected == item.id,
                        item = item,
                        onExpandClick = {
                            idCharacterSelected = if (idCharacterSelected == item.id) null
                            else item.id
                        },
                        onDetailCharacter = {
                            onNavigateToDetailCharacter(it)
                        },
                        onViewEpisodes = {
                            onNavigateToViewEpisodes(it)
                        }
                    )
                }*/
            }
        }
    }

    if (isLoading) {
        LoadingDialog()
    }
    if (messageError.isNotBlank()) {
        val title =
            if (homeViewmodel.isConnected) "Error en el servidor" else "Sin internet"
        DialogAlert(
            title = title,
            message = messageError,
            textPositiveButton = "Aceptar",
            onPositiveCallback = {
                messageError = ""
            }
        )
    }

}

@Preview
@Composable
fun PreviewHOmeScreen() {
    HomeScreen(
        onNavigateToViewEpisodes = {},
        onNavigateToDetailCharacter = {},
        homeViewmodel = fakeViewModel
    )
}

val fakeViewModel = HomeViewmodel(
    totalPagesUsesCase = fakeTotalPagesUsesCase,
    getAllCharactersCombineUseCase = fakeGetAllCharactersCombineUseCase,
    getSingleCharacterByNameCombineUseCase = fakeGetSingleCharacterByNameCombineUseCase,
)