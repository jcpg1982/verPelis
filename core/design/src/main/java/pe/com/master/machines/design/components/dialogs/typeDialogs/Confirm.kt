package pe.com.master.machines.design.components.dialogs.typeDialogs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import pe.com.master.machines.design.components.buttom.ActionButtonsDialog
import pe.com.master.machines.design.components.text.CustomText
import pe.com.master.machines.design.components.text.MessageDialog
import pe.com.master.machines.design.theme.ColorWhite
import pe.com.master.machines.design.theme.ContentInset

@Composable
fun Confirm(
    title: String,
    message: String,
    textPositiveButton: String,
    textColorPositiveButton: Color,
    backgroundColorPositiveButton: Color,
    onPositiveCallback: () -> Unit,
    textNegativeButton: String,
    textColorNegativeButton: Color,
    backgroundColorNegativeButton: Color,
    onNegativeCallback: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = ColorWhite)
            .padding(ContentInset)
    ) {
        CustomText(text = title)
        MessageDialog(message = message)
        ActionButtonsDialog(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = ContentInset),
            textPositiveButton = textPositiveButton,
            textColorPositiveButton = textColorPositiveButton,
            backgroundColorPositiveButton = backgroundColorPositiveButton,
            onPositiveCallback = onPositiveCallback,
            textNegativeButton = textNegativeButton,
            textColorNegativeButton = textColorNegativeButton,
            backgroundColorNegativeButton = backgroundColorNegativeButton,
            onNegativeCallback = onNegativeCallback
        )
    }
}