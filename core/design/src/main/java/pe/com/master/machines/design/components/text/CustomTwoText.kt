package pe.com.master.machines.design.components.text

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import pe.com.master.machines.design.theme.BlueGray400
import pe.com.master.machines.design.theme.BlueGray500
import pe.com.master.machines.design.theme.BlueGray700
import pe.com.master.machines.design.theme.BlueGray900
import pe.com.master.machines.design.theme.DynamicTextSixteen

@Composable
fun CustomTwoText(
    title: String,
    subTitle: String,
    modifier: Modifier = Modifier,
    textAlign: TextAlign = TextAlign.Start,
    fontSize: TextUnit = DynamicTextSixteen,
) {
    val fontSizeSubTitle = TextUnit(fontSize.value - 2, fontSize.type)
    Column(
        modifier = modifier
    ) {
        CustomText(
            modifier = Modifier.fillMaxWidth(),
            text = title,
            fontSize = fontSize,
            fontWeight = FontWeight.Bold,
            textColor = BlueGray900,
            maxLines = 1,
            textAlign = textAlign,
        )
        CustomText(
            modifier = Modifier.fillMaxWidth(),
            text = subTitle,
            fontSize = fontSizeSubTitle,
            fontWeight = FontWeight.Normal,
            textColor = BlueGray700,
            maxLines = 3,
            textAlign = textAlign,
        )
    }
}

@Preview
@Composable
fun PreviewCustomTwoTextt() {
    CustomTwoText(
        title = "Jorge luna",
        subTitle = "Lo maximo"
    )
}