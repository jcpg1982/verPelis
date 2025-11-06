package pe.com.master.machines.design.components.buttom

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import pe.com.master.machines.design.components.text.TextClickButton
import pe.com.master.machines.design.theme.ContentInset

@Composable
fun ActionButtonsDialog(
    modifier: Modifier = Modifier,
    textPositiveButton: String,
    textColorPositiveButton: Color,
    backgroundColorPositiveButton: Color,
    onPositiveCallback: () -> Unit,
    textNegativeButton: String? = null,
    textColorNegativeButton: Color? = null,
    backgroundColorNegativeButton: Color? = null,
    onNegativeCallback: (() -> Unit)? = null
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.End
    ) {
        if (!textNegativeButton.isNullOrBlank() && onNegativeCallback != null) {
            TextClickButton(
                modifier = Modifier.wrapContentWidth(),
                textButton = textNegativeButton,
                textColorButton = textColorNegativeButton ?: Color.Gray,
                backgroundColorButton = backgroundColorNegativeButton
                    ?: Color.Transparent,
                onClick = onNegativeCallback
            )
            Spacer(modifier = Modifier.width(ContentInset))
        }
        TextClickButton(
            modifier = Modifier.wrapContentWidth(),
            textButton = textPositiveButton,
            textColorButton = textColorPositiveButton,
            backgroundColorButton = backgroundColorPositiveButton,
            onClick = onPositiveCallback
        )
    }
}

@Preview
@Composable
fun PreviewActionButtonsRow() {

    Column {
        ActionButtonsDialog(
            modifier = Modifier.fillMaxWidth(),
            textPositiveButton = "Aceptar",
            textColorPositiveButton = Color.White,
            backgroundColorPositiveButton = Color.Green,
            onPositiveCallback = { },
            textNegativeButton = "Cancelar",
            textColorNegativeButton = Color.White,
            backgroundColorNegativeButton = Color.Red,
            onNegativeCallback = {  }
        )

        Spacer(modifier = Modifier.height(ContentInset))
        ActionButtonsDialog(
            modifier = Modifier.fillMaxWidth(),
            textPositiveButton = "Aceptar",
            textColorPositiveButton = Color.White,
            backgroundColorPositiveButton = Color.Green,
            onPositiveCallback = {  },
            textNegativeButton = "",
            textColorNegativeButton = Color.White,
            backgroundColorNegativeButton = Color.Red,
            onNegativeCallback = {  }
        )
    }
}
