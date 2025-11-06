package pe.com.master.machines.design.components.text

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import pe.com.master.machines.design.theme.DynamicTextSixteen
import pe.com.master.machines.design.theme.Turquoise500
import pe.com.master.machines.design.theme.robotoRegular

@Composable
fun CustomText(
    text: String,
    modifier: Modifier = Modifier,
    textColor: Color = Turquoise500,
    fontSize: TextUnit = DynamicTextSixteen,
    minLines: Int = 1,
    maxLines: Int = 1,
    fontWeight: FontWeight = FontWeight.Normal,
    textAlign: TextAlign = TextAlign.Start,
    fontFamily: FontFamily = FontFamily(robotoRegular),
    overflow: TextOverflow = TextOverflow.Ellipsis
) {
    Text(
        text = text,
        modifier = modifier,
        fontSize = fontSize,
        fontWeight = fontWeight,
        color = textColor,
        textAlign = textAlign,
        overflow = overflow,
        minLines = minLines,
        maxLines = maxLines,
        lineHeight = fontSize * 1.5,
        fontFamily = fontFamily
    )
}

@Preview
@Composable
fun PreviewCustomText() {
    CustomText(text = "Jorge luna")
}