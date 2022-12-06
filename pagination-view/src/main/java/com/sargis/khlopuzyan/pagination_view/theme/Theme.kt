package com.sargis.khlopuzyan.pagination_view.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.sargis.khlopuzyan.pagination_view.R

private val DarkColorPalette = darkColors(
    primary = Purple200,
    primaryVariant = Purple700,
    secondary = Teal200
)

private val LightColorPalette = lightColors(
    primary = Purple500,
    primaryVariant = Purple700,
    secondary = Teal200

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun PaginationTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}



val DinFont = FontFamily(
    Font(R.font.din_regular, weight = FontWeight.Normal),
    Font(R.font.din_medium, weight = FontWeight.Medium),
    Font(R.font.din_bold, weight = FontWeight.Bold),
    Font(R.font.din_black, weight = FontWeight.Black),
)

val NumericPaginationItemText: TextStyle
    @Composable
    get() {
        return TextStyle(
            fontFamily = DinFont,
            fontWeight = FontWeight.Bold,
            fontSize = dimensionResource(R.dimen.sp_18).value.sp
        )
    }