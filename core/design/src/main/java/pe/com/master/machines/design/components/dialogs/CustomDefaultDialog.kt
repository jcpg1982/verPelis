package pe.com.master.machines.design.components.dialogs

import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedCard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import pe.com.master.machines.design.components.dialogs.typeDialogs.Alert
import pe.com.master.machines.design.components.dialogs.typeDialogs.Confirm
import pe.com.master.machines.design.theme.ColorBlack
import pe.com.master.machines.design.theme.ContentInset
import pe.com.master.machines.model.enums.DialogType

@Composable
inline fun <reified T> CustomDefaultDialog(
    title: String? = null,
    message: String? = null,
    dialogType: DialogType,
    isCancelable: Boolean,
    primaryColor: Color? = null,
    colorText: Color = ColorBlack,
    hintSearch: String? = null,
    hintOthers: String? = null,
    listItems: List<T>? = null,
    textPositiveButton: String? = null,
    textColorPositiveButton: Color? = null,
    backgroundColorPositiveButton: Color? = null,
    crossinline onPositiveCallback: () -> Unit = {},
    textNegativeButton: String? = null,
    textColorNegativeButton: Color? = null,
    backgroundColorNegativeButton: Color? = null,
    crossinline onNegativeCallback: () -> Unit = {},
    crossinline onItemsCallback: (Int, T) -> Unit = { pos, data -> },
    crossinline onInputCallback: (String) -> Unit = {},
    crossinline onInputSearchCallback: () -> Unit = {},
    crossinline onDismissDialog: () -> Unit = {},
) {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    Dialog(
        onDismissRequest = { onDismissDialog() }, properties = DialogProperties(
            dismissOnBackPress = isCancelable, dismissOnClickOutside = isCancelable
        )
    ) {
        OutlinedCard(
            modifier = Modifier
                .widthIn(min = screenWidth * 0.20f, max = screenWidth * 0.75f)
                .clip(RoundedCornerShape(ContentInset))
        ) {
            when (dialogType) {
                DialogType.ALERT -> {
                    title?.let { t ->
                        message?.let { m ->
                            textPositiveButton?.let { tpb ->
                                textColorPositiveButton?.let { tcpb ->
                                    backgroundColorPositiveButton?.let { bcpb ->
                                        Alert(
                                            title = t,
                                            message = m,
                                            textPositiveButton = tpb,
                                            textColorPositiveButton = tcpb,
                                            backgroundColorPositiveButton = bcpb,
                                            onPositiveCallback = { onPositiveCallback() }
                                        )
                                    }
                                }
                            }
                        }
                    }
                }

                DialogType.CONFIRM -> {
                    title?.let { t ->
                        message?.let { m ->
                            textPositiveButton?.let { tpb ->
                                textColorPositiveButton?.let { tcpb ->
                                    backgroundColorPositiveButton?.let { bcpb ->
                                        textNegativeButton?.let { tnb ->
                                            textColorNegativeButton?.let { tcnb ->
                                                backgroundColorNegativeButton?.let { bcnb ->
                                                    Confirm(
                                                        title = t,
                                                        message = m,
                                                        textPositiveButton = tpb,
                                                        textColorPositiveButton = tcpb,
                                                        backgroundColorPositiveButton = bcpb,
                                                        onPositiveCallback = { onPositiveCallback() },
                                                        textNegativeButton = tnb,
                                                        textColorNegativeButton = tcnb,
                                                        backgroundColorNegativeButton = bcnb,
                                                        onNegativeCallback = { onNegativeCallback() }
                                                    )
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

                DialogType.OTHERS -> {}
                DialogType.ITEMS -> {}

                DialogType.ITEMS_OTHERS -> {}

                DialogType.ITEMS_SEARCH -> {}

                DialogType.ITEMS_OTHERS_AND_SEARCH -> {}
                DialogType.ITEMS_ALERT -> {}
            }
        }
    }
}

@Preview
@Composable
fun PreviewDialogDefault() {
    CustomDefaultDialog<String>(
        title = "Error en el servidor",
        message = "Este es el cuerpo del dialogo",
        dialogType = DialogType.CONFIRM,
        isCancelable = false,
        primaryColor = Color.Red,
        hintSearch = "Search country",
        hintOthers = null,
        listItems = listOf(
            "Item 1",
            "Item 2",
            "Item 3",
            "Item 4",
            "Item 5",
            "Item 6",
        ),
        textPositiveButton = "Aceptar",
        textColorPositiveButton = Color.Blue,
        backgroundColorPositiveButton = Color.White,
        textNegativeButton = null,
        textColorNegativeButton = null,
        backgroundColorNegativeButton = null,
        onPositiveCallback = { },
        onDismissDialog = {},
        onNegativeCallback = { },
        onItemsCallback = { pos, data -> },
        onInputCallback = { },
        onInputSearchCallback = { })
}