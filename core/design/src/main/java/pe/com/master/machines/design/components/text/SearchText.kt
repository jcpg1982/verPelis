package pe.com.master.machines.design.components.text

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import pe.com.master.machines.design.theme.BlueGray500
import pe.com.master.machines.design.theme.DynamicTextSixteen
import pe.com.master.machines.design.theme.robotoRegular

@Composable
fun SearchText(
    hintSearch: String,
    maxCharacter: Int,
    modifier: Modifier = Modifier,
    onMessageSearch: (String) -> Unit
) {

    var filterName by remember { mutableStateOf("") }

    OutlinedTextField(
        value = filterName,
        onValueChange = {
            if (it.length <= maxCharacter) {
                filterName = it
                onMessageSearch(filterName.lowercase())
            }
        },
        modifier = modifier.fillMaxWidth(),
        label = {
            CustomText(
                text = hintSearch,
                fontSize = DynamicTextSixteen,
                fontFamily = FontFamily(robotoRegular)
            )
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = "open drop down",
                tint = BlueGray500
            )
        },
        trailingIcon = {
            if (filterName.isNotEmpty()) {
                Icon(
                    imageVector = Icons.Filled.Close,
                    contentDescription = "open drop down",
                    modifier = Modifier.clickable {
                        filterName = ""
                        onMessageSearch("")
                    },
                    tint = BlueGray500
                )
            }
        },
        minLines = 1,
        maxLines = 1
    )
}

@Preview
@Composable
fun PreviewSearchText() {
    SearchText(
        hintSearch = "Buscar",
        maxCharacter = 20,
        onMessageSearch = {}
    )
}