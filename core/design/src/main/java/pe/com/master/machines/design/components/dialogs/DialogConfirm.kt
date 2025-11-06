package pe.com.master.machines.design.components.dialogs

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import pe.com.master.machines.design.theme.ColorWhite
import pe.com.master.machines.design.theme.Orange500
import pe.com.master.machines.design.theme.Red500
import pe.com.master.machines.design.theme.Turquoise500
import pe.com.master.machines.model.enums.DialogType

@Composable
fun DialogConfirm(
    title: String,
    message: String,
    isCancelable: Boolean,
    textPositiveButton: String,
    textNegativeButton: String,
    backgroundColorPositiveButton: Color = Red500,
    textColorPositiveButton: Color = ColorWhite,
    backgroundColorNegativeButton: Color = Turquoise500,
    textColorNegativeButton: Color = ColorWhite,
    onPositiveCallback: () -> Unit,
    onNegativeCallback: () -> Unit,
    onDismissDialog: () -> Unit = {}
) {
    CustomDefaultDialog<Any>(
        title = title,
        message = message,
        dialogType = DialogType.CONFIRM,
        isCancelable = isCancelable,
        textPositiveButton = textPositiveButton,
        textColorPositiveButton = textColorPositiveButton,
        backgroundColorPositiveButton = backgroundColorPositiveButton,
        onPositiveCallback = onPositiveCallback,
        textNegativeButton = textNegativeButton,
        textColorNegativeButton = textColorNegativeButton,
        backgroundColorNegativeButton = backgroundColorNegativeButton,
        onNegativeCallback = onNegativeCallback,
        onDismissDialog = onDismissDialog
    )
}

@Preview
@Composable
fun PreviewDialogConfirm() {
    DialogConfirm(
        title = "titulo",
        message = "mensaje",
        isCancelable = false,
        textPositiveButton = "aceptar",
        textColorPositiveButton = Turquoise500,
        backgroundColorPositiveButton = ColorWhite,
        onPositiveCallback = { },
        textNegativeButton = "cancelar",
        textColorNegativeButton = Orange500,
        backgroundColorNegativeButton = ColorWhite,
        onNegativeCallback = { }) {

    }
}