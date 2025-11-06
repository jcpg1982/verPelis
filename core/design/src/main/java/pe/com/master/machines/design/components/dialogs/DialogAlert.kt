package pe.com.master.machines.design.components.dialogs

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import pe.com.master.machines.design.theme.ColorWhite
import pe.com.master.machines.design.theme.Turquoise500
import pe.com.master.machines.model.enums.DialogType

@Composable
fun DialogAlert(
    title: String,
    message: String,
    textPositiveButton: String,
    textColorPositiveButton: Color = ColorWhite,
    backgroundColorPositiveButton: Color = Turquoise500,
    isCancelable: Boolean = false,
    onPositiveCallback: () -> Unit,
    onDismissDialog: () -> Unit = {}
) {
    CustomDefaultDialog<Any>(
        title = title,
        message = message,
        dialogType = DialogType.ALERT,
        isCancelable = isCancelable,
        textPositiveButton = textPositiveButton,
        textColorPositiveButton = textColorPositiveButton,
        backgroundColorPositiveButton = backgroundColorPositiveButton,
        onPositiveCallback = { onPositiveCallback() },
        onNegativeCallback = { onDismissDialog() },
        onDismissDialog = { onDismissDialog() }
    )
}

@Preview
@Composable
fun PreviewDialogAlert() {
    DialogAlert(
        title = "Error en el servidor",
        message = "Este es el cuerpo del dialogo ñ{li hlaifeñruh cfveñrkljhe wrñokjternojdf kñjdfñ ogjnf hñoslkjfgn bojdfgn ñojdns boi hnñkj hbilyfgiyuftr dcikjlyhfg jytrdclkjhdbvkjl weli edlki eliñu dfhglidyfu giñldfu hgoiyu goiy goiyu gpoiu hpoiu hgoiuytfgiutfiyubgñoijh o´pihoiuyfgyutredsytredikjubhkljbkjgbvc tgdsxiukhgvlkjh bvgkyhfdsghcvlkjh bljh gfuydfxiygvlikjhb ilyhgviyutfcdiuh gbvkiljhboilyhfgiuhgfvoihb kiljbhoiluygiutgfcviuhbvokjhlgiugdfojhbgkñljhgkugfvlujhgvljhg",
        isCancelable = false,
        textPositiveButton = "Aceptar",
        textColorPositiveButton = Color.Blue,
        backgroundColorPositiveButton = Color.White,
        onPositiveCallback = { },
        onDismissDialog = {}
    )
}

@Preview
@Composable
fun PreviewDialogAlertSmall() {
    DialogAlert(
        title = "Error en el servidor",
        message = "Este es el cuerpo del dialogo",
        isCancelable = false,
        textPositiveButton = "Aceptar",
        textColorPositiveButton = Color.Blue,
        backgroundColorPositiveButton = Color.White,
        onPositiveCallback = { },
        onDismissDialog = {}
    )
}