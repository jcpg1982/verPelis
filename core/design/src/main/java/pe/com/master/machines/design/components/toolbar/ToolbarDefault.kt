package pe.com.master.machines.design.components.toolbar

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import pe.com.master.machines.design.components.text.CustomText
import pe.com.master.machines.design.theme.DynamicTextTwentyTwo
import pe.com.master.machines.design.theme.notoSerifSemiBold

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToolbarDefault(
    title: String,
    modifier: Modifier = Modifier,
    showIconBack: Boolean = false,
    textAlign: TextAlign = TextAlign.Start,
    colorBackground: Color = Color.White,
    colorIcons: Color = Color.Black,
    onClickBack: () -> Unit = {},
) {

    val systemUiController = rememberSystemUiController()
    val useDarkIcons = colorBackground.luminance() > 0.5f
    SideEffect {
        systemUiController.setSystemBarsColor(
            color = colorBackground,
            darkIcons = useDarkIcons
        )
    }

    TopAppBar(
        modifier = modifier
            .fillMaxWidth(),
        colors = TopAppBarColors(
            containerColor = colorBackground,
            scrolledContainerColor = Color.Black,
            navigationIconContentColor = Color.Black,
            titleContentColor = Color.Gray,
            actionIconContentColor = Color.Black,
        ),
        title = {
            CustomText(
                text = title,
                modifier = Modifier
                    .fillMaxWidth(),
                textAlign = textAlign,
                fontWeight = FontWeight.W800,
                fontFamily = FontFamily(notoSerifSemiBold),
                textColor = colorIcons,
                fontSize = DynamicTextTwentyTwo
            )
        },
        navigationIcon = {
            if (showIconBack) {
                IconButton(onClick = { onClickBack() }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Menu",
                        tint = colorIcons
                    )
                }
            }
        },
    )
}

@Preview
@Composable
fun PreviewToolbarDefault() {
    Column {
        ToolbarDefault(
            showIconBack = true,
            title = "Titulo",
            onClickBack = {}
        )
        ToolbarDefault(
            title = "Titulo",
            showIconBack = true,
            onClickBack = {}
        )
        ToolbarDefault(
            title = "Titulo",
            showIconBack = true,
            onClickBack = {}
        )
        ToolbarDefault(
            title = "Titulo",
            textAlign = TextAlign.Center,
            onClickBack = {}
        )
        ToolbarDefault(
            showIconBack = true,
            title = "Titulo",
            textAlign = TextAlign.Center,
            onClickBack = {}
        )
        ToolbarDefault(
            title = "Titulo",
            textAlign = TextAlign.Center,
            onClickBack = {}
        )
        ToolbarDefault(
            title = "Titulo",
            textAlign = TextAlign.Center,
            onClickBack = {}
        )
    }
}