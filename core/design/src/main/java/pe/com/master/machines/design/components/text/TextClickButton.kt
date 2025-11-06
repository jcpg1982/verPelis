package pe.com.master.machines.design.components.text

import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import pe.com.master.machines.design.theme.ContentInsetFive
import pe.com.master.machines.design.theme.ContentInsetFour
import pe.com.master.machines.design.theme.DynamicTextFourteen

@Composable
fun TextClickButton(
    modifier: Modifier,
    textButton: String,
    textColorButton: Color,
    backgroundColorButton: Color,
    onClick: () -> Unit
) {
    val rounded = RoundedCornerShape(ContentInsetFour)
    TextButton(
        onClick = { onClick() },
        modifier = modifier,
        shape = rounded,
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColorButton, contentColor = textColorButton
        ),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = ContentInsetFive
        )
    ) {
        CustomText(
            text = textButton,
            fontSize = DynamicTextFourteen,
            maxLines = 1,
            textColor = textColorButton,
        )
    }
}

@Preview
@Composable
fun GetPreviewTextClickButton(modifier: Modifier = Modifier) {
    TextClickButton(
        modifier = Modifier.wrapContentWidth(),
        textButton = "Aceptar",
        textColorButton = Color.Blue,
        backgroundColorButton = Color.White
    ) { }
}