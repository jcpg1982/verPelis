package pe.com.master.machines.design.components.dialogs

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import pe.com.master.machines.design.R
import pe.com.master.machines.design.theme.ColorWhite
import pe.com.master.machines.design.theme.ContentInset
import pe.com.master.machines.design.theme.ContentInsetEight
import pe.com.master.machines.design.theme.ContentInsetFour
import pe.com.master.machines.design.theme.ContentInsetOneHundred
import pe.com.master.machines.design.theme.ContentInsetOneHundredFifty
import pe.com.master.machines.design.theme.DynamicTextSixteen
import pe.com.master.machines.design.theme.Turquoise500

@Composable
fun LoadingDialog(
    message: String = "Cargando información",
    primaryColor: Color = Turquoise500
) {
    Dialog(
        onDismissRequest = { }, properties = DialogProperties(
            dismissOnBackPress = false, dismissOnClickOutside = false
        )
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(modifier = Modifier.wrapContentSize()) {
                CircularProgressIndicator(
                    color = primaryColor,
                    strokeWidth = ContentInsetFour,
                    modifier = Modifier
                        .size(ContentInsetOneHundredFifty)
                        .align(Alignment.Center)
                )
                Image(
                    painter = painterResource(id = R.drawable.ic_action_loading),
                    contentDescription = "Image Loading",
                    modifier = Modifier
                        .size(ContentInsetOneHundred)
                        .clip(CircleShape)
                        .align(Alignment.Center)
                        .padding(ContentInsetEight),
                    alignment = Alignment.Center
                )
            }

            Text(
                text = message,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = ContentInset, end = ContentInset, top = ContentInset)
                    .align(Alignment.CenterHorizontally),
                color = ColorWhite,
                fontSize = DynamicTextSixteen,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview
@Composable
fun PreviewLoadingDialog() {
    LoadingDialog(
        message = "Cargando",
        primaryColor = Turquoise500
    )
}

