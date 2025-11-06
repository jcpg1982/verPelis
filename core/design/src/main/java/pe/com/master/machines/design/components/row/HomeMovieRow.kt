package pe.com.master.machines.design.components.row

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pe.com.master.machines.design.components.images.ImageWithUrl
import pe.com.master.machines.design.components.text.CustomText
import pe.com.master.machines.design.theme.BlueGray500
import pe.com.master.machines.design.theme.ColorWhite
import pe.com.master.machines.design.theme.ContentInset
import pe.com.master.machines.design.theme.ContentInsetEight
import pe.com.master.machines.design.theme.ContentInsetOneHundredFifty
import pe.com.master.machines.design.theme.DynamicTextTwentySix
import pe.com.master.machines.design.theme.TextName
import pe.com.master.machines.model.model.Movie

@Composable
fun HomeMovieRow(
    isExpanded: Boolean,
    item: Movie,
    onExpandClick: (Int) -> Unit,
    onDetailCharacter: (Int) -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onExpandClick(item.id)
            }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .background(color = ColorWhite), verticalAlignment = Alignment.Top
        ) {

            ImageWithUrl(
                photoUrl = "https://image.tmdb.org/t/p/w500${item.backdropPath}",
                modifier = Modifier
                    .padding(all = ContentInsetEight)
                    .size(ContentInsetOneHundredFifty)
                    .clip(RoundedCornerShape(ContentInset)),
                contentScale = ContentScale.Crop,
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f), verticalArrangement = Arrangement.Center
            ) {
                CustomText(
                    text = item.title,
                    textColor = TextName,
                    fontWeight = FontWeight.Bold,
                    fontSize = DynamicTextTwentySix,
                    maxLines = 2
                )

                Spacer(modifier = Modifier.height(ContentInset))

                CustomText(
                    text = item.originalTitle,
                    textColor = BlueGray500,
                    maxLines = 2
                )

                Spacer(modifier = Modifier.height(ContentInset))

                CustomText(
                    text = item.overview,
                    textColor = TextName,
                    maxLines = 10
                )
            }
        }
        AnimatedVisibility(visible = isExpanded) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = ContentInsetEight),
                horizontalArrangement = Arrangement.End
            ) {
                Button(onClick = {
                    onDetailCharacter(item.id)
                }) {
                    CustomText(text = "Ver Detalles")
                }
                Spacer(modifier = Modifier.width(8.dp))
            }
        }
    }
}

@Preview
@Composable
fun PreviewRowEvent() {
    Column {
        HomeMovieRow(
            isExpanded = false,
            item = Movie(),
            onExpandClick = {},
            onDetailCharacter = {},
        )
        HomeMovieRow(
            isExpanded = true,
            item = Movie(),
            onExpandClick = {},
            onDetailCharacter = {},
        )
    }
}
