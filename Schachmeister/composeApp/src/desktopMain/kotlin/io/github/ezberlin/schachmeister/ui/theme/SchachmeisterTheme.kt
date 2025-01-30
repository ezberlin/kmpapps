package io.github.ezberlin.schachmeister.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import org.jetbrains.compose.resources.Font
import schachmeister.composeapp.generated.resources.*
import schachmeister.composeapp.generated.resources.ReadexPro_Bold
import schachmeister.composeapp.generated.resources.Res

@Composable
fun SchachmeisterTheme(
    content: @Composable () -> Unit
) {
    val readexProFontFamily = FontFamily(
        Font(
            resource = Res.font.ReadexPro_Bold,
            weight = FontWeight.Bold
        ),
        Font(
            resource = Res.font.ReadexPro_SemiBold,
            weight = FontWeight.SemiBold
        ),
        Font(
            resource = Res.font.ReadexPro_Medium,
            weight = FontWeight.Medium
        ),
        Font(
            resource = Res.font.ReadexPro_Regular,
            weight = FontWeight.Normal
        ),
        Font(
            resource = Res.font.ReadexPro_Light,
            weight = FontWeight.Light
        ),
        Font(
            resource = Res.font.ReadexPro_ExtraLight,
            weight = FontWeight.ExtraLight
        )
    )

    val typography = Typography(
        defaultFontFamily = readexProFontFamily,
    )

    MaterialTheme(
        typography = typography,
        content = content
    )
}