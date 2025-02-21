@file:OptIn(org.jetbrains.compose.resources.InternalResourceApi::class)

package schachmeister.composeapp.generated.resources

import kotlin.OptIn
import kotlin.String
import kotlin.collections.MutableMap
import org.jetbrains.compose.resources.FontResource
import org.jetbrains.compose.resources.InternalResourceApi

private object CommonMainFont0 {
  public val ReadexPro_Bold: FontResource by 
      lazy { init_ReadexPro_Bold() }

  public val ReadexPro_ExtraLight: FontResource by 
      lazy { init_ReadexPro_ExtraLight() }

  public val ReadexPro_Light: FontResource by 
      lazy { init_ReadexPro_Light() }

  public val ReadexPro_Medium: FontResource by 
      lazy { init_ReadexPro_Medium() }

  public val ReadexPro_Regular: FontResource by 
      lazy { init_ReadexPro_Regular() }

  public val ReadexPro_SemiBold: FontResource by 
      lazy { init_ReadexPro_SemiBold() }
}

@InternalResourceApi
internal fun _collectCommonMainFont0Resources(map: MutableMap<String, FontResource>) {
  map.put("ReadexPro_Bold", CommonMainFont0.ReadexPro_Bold)
  map.put("ReadexPro_ExtraLight", CommonMainFont0.ReadexPro_ExtraLight)
  map.put("ReadexPro_Light", CommonMainFont0.ReadexPro_Light)
  map.put("ReadexPro_Medium", CommonMainFont0.ReadexPro_Medium)
  map.put("ReadexPro_Regular", CommonMainFont0.ReadexPro_Regular)
  map.put("ReadexPro_SemiBold", CommonMainFont0.ReadexPro_SemiBold)
}

internal val Res.font.ReadexPro_Bold: FontResource
  get() = CommonMainFont0.ReadexPro_Bold

private fun init_ReadexPro_Bold(): FontResource = org.jetbrains.compose.resources.FontResource(
  "font:ReadexPro_Bold",
    setOf(
      org.jetbrains.compose.resources.ResourceItem(setOf(),
    "composeResources/schachmeister.composeapp.generated.resources/font/ReadexPro-Bold.ttf", -1, -1),
    )
)

internal val Res.font.ReadexPro_ExtraLight: FontResource
  get() = CommonMainFont0.ReadexPro_ExtraLight

private fun init_ReadexPro_ExtraLight(): FontResource =
    org.jetbrains.compose.resources.FontResource(
  "font:ReadexPro_ExtraLight",
    setOf(
      org.jetbrains.compose.resources.ResourceItem(setOf(),
    "composeResources/schachmeister.composeapp.generated.resources/font/ReadexPro-ExtraLight.ttf", -1, -1),
    )
)

internal val Res.font.ReadexPro_Light: FontResource
  get() = CommonMainFont0.ReadexPro_Light

private fun init_ReadexPro_Light(): FontResource = org.jetbrains.compose.resources.FontResource(
  "font:ReadexPro_Light",
    setOf(
      org.jetbrains.compose.resources.ResourceItem(setOf(),
    "composeResources/schachmeister.composeapp.generated.resources/font/ReadexPro-Light.ttf", -1, -1),
    )
)

internal val Res.font.ReadexPro_Medium: FontResource
  get() = CommonMainFont0.ReadexPro_Medium

private fun init_ReadexPro_Medium(): FontResource = org.jetbrains.compose.resources.FontResource(
  "font:ReadexPro_Medium",
    setOf(
      org.jetbrains.compose.resources.ResourceItem(setOf(),
    "composeResources/schachmeister.composeapp.generated.resources/font/ReadexPro-Medium.ttf", -1, -1),
    )
)

internal val Res.font.ReadexPro_Regular: FontResource
  get() = CommonMainFont0.ReadexPro_Regular

private fun init_ReadexPro_Regular(): FontResource = org.jetbrains.compose.resources.FontResource(
  "font:ReadexPro_Regular",
    setOf(
      org.jetbrains.compose.resources.ResourceItem(setOf(),
    "composeResources/schachmeister.composeapp.generated.resources/font/ReadexPro-Regular.ttf", -1, -1),
    )
)

internal val Res.font.ReadexPro_SemiBold: FontResource
  get() = CommonMainFont0.ReadexPro_SemiBold

private fun init_ReadexPro_SemiBold(): FontResource = org.jetbrains.compose.resources.FontResource(
  "font:ReadexPro_SemiBold",
    setOf(
      org.jetbrains.compose.resources.ResourceItem(setOf(),
    "composeResources/schachmeister.composeapp.generated.resources/font/ReadexPro-SemiBold.ttf", -1, -1),
    )
)
