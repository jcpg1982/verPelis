package pe.com.master.machines.design.components.text

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign.Companion.Justify
import androidx.compose.ui.tooling.preview.Preview
import pe.com.master.machines.design.theme.BlueGray500
import pe.com.master.machines.design.theme.ContentInset
import pe.com.master.machines.design.theme.ContentInsetThreeHundred
import pe.com.master.machines.design.theme.DynamicTextSixteen

@Composable
fun MessageDialog(
    message: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(top = ContentInset)
            .width(IntrinsicSize.Max)
            .heightIn(max = ContentInsetThreeHundred)
            .verticalScroll(rememberScrollState())
    ) {
        CustomText(
            text = message,
            modifier = Modifier
                .fillMaxSize(),
            fontSize = DynamicTextSixteen,
            textColor = BlueGray500,
            textAlign = Justify,
            maxLines = 50
        )
    }
}

@Preview
@Composable
fun PreviewMessageDialog() {
    MessageDialog("Testñlkjbh")
}