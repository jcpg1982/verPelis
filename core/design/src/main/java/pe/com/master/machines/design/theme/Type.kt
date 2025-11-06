package pe.com.master.machines.design.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import pe.com.master.machines.design.R

val interRegular = Font(R.font.inter_regular, FontWeight.Normal)
val interSemiBold = Font(R.font.inter_semi_bold, FontWeight.SemiBold)
val robotoRegular = Font(R.font.roboto_regular, FontWeight.Normal)
val notoSerifRegular = Font(R.font.noto_serif_regular, FontWeight.Normal)
val notoSerifSemiBold = Font(R.font.noto_serif_semi_bold, FontWeight.SemiBold)
val patuaOneRegular = Font(R.font.patua_one_regular, FontWeight.Normal)

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily(robotoRegular),
        fontWeight = FontWeight.Normal,
        fontSize = DynamicTextSixteen,
        lineHeight = DynamicTextSixteen * 1.3,
        letterSpacing = DynamicPutFive
    )
)