package pe.com.master.machines.design.components.row

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import pe.com.master.machines.design.components.text.CustomText
import pe.com.master.machines.design.theme.BlueGray800
import pe.com.master.machines.design.theme.BlueGray900
import pe.com.master.machines.design.theme.ContentInset
import pe.com.master.machines.design.theme.ContentInsetEight
import pe.com.master.machines.design.theme.ContentInsetFour
import pe.com.master.machines.design.theme.ContentInsetTwelve
import pe.com.master.machines.design.theme.DynamicTextFourteen
import pe.com.master.machines.design.theme.DynamicTextSixteen

/*@Composable
fun EpisodeRow(
    item: Episode,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(ContentInsetTwelve),
        elevation = CardDefaults.cardElevation(defaultElevation = ContentInsetFour)
    ) {
        Column(modifier = Modifier.padding(ContentInset)) {

            CustomText(
                text = item.name,
                textColor = BlueGray900,
                fontWeight = FontWeight.Bold,
                fontSize = DynamicTextSixteen,
                maxLines = 2
            )

            CustomText(
                text = item.episode,
                textColor = BlueGray800,
                maxLines = 1,
            )

            Spacer(modifier = Modifier.height(ContentInsetFour))

            CustomText(
                text = "Air date: ${item.airDate}",
                fontSize = DynamicTextFourteen,
                maxLines = 1,
                textColor = BlueGray800,
            )

            Spacer(modifier = Modifier.height(ContentInsetEight))

            CustomText(
                text = "Characters: ${item.characters.size}",
                fontSize = DynamicTextFourteen,
                maxLines = 1,
                textColor = BlueGray800,
            )
        }
    }
}

@Preview
@Composable
fun PreviewEpisodeRow() {
    Column {
        EpisodeRow(
            item = Episode(
                name = "Nombre del episodio",
                episode = "SO2451"
            ),
        )
        EpisodeRow(
            item = Episode(
                name = "Nombre del episodio",
                episode = "SO2451"
            ),
        )
    }
}*/
