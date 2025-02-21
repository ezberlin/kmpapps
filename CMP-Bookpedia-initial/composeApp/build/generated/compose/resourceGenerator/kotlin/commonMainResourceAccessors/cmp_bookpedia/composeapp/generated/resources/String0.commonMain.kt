@file:OptIn(org.jetbrains.compose.resources.InternalResourceApi::class)

package cmp_bookpedia.composeapp.generated.resources

import kotlin.OptIn
import kotlin.String
import kotlin.collections.MutableMap
import org.jetbrains.compose.resources.InternalResourceApi
import org.jetbrains.compose.resources.StringResource

private object CommonMainString0 {
  public val close_hint: StringResource by 
      lazy { init_close_hint() }

  public val favorites: StringResource by 
      lazy { init_favorites() }

  public val no_favorite_books: StringResource by 
      lazy { init_no_favorite_books() }

  public val no_search_results: StringResource by 
      lazy { init_no_search_results() }

  public val search_hint: StringResource by 
      lazy { init_search_hint() }

  public val search_results: StringResource by 
      lazy { init_search_results() }
}

@InternalResourceApi
internal fun _collectCommonMainString0Resources(map: MutableMap<String, StringResource>) {
  map.put("close_hint", CommonMainString0.close_hint)
  map.put("favorites", CommonMainString0.favorites)
  map.put("no_favorite_books", CommonMainString0.no_favorite_books)
  map.put("no_search_results", CommonMainString0.no_search_results)
  map.put("search_hint", CommonMainString0.search_hint)
  map.put("search_results", CommonMainString0.search_results)
}

internal val Res.string.close_hint: StringResource
  get() = CommonMainString0.close_hint

private fun init_close_hint(): StringResource = org.jetbrains.compose.resources.StringResource(
  "string:close_hint", "close_hint",
    setOf(
      org.jetbrains.compose.resources.ResourceItem(setOf(),
    "composeResources/cmp_bookpedia.composeapp.generated.resources/values/strings.commonMain.cvr",
    10, 30),
    )
)

internal val Res.string.favorites: StringResource
  get() = CommonMainString0.favorites

private fun init_favorites(): StringResource = org.jetbrains.compose.resources.StringResource(
  "string:favorites", "favorites",
    setOf(
      org.jetbrains.compose.resources.ResourceItem(setOf(),
    "composeResources/cmp_bookpedia.composeapp.generated.resources/values/strings.commonMain.cvr",
    41, 29),
    )
)

internal val Res.string.no_favorite_books: StringResource
  get() = CommonMainString0.no_favorite_books

private fun init_no_favorite_books(): StringResource =
    org.jetbrains.compose.resources.StringResource(
  "string:no_favorite_books", "no_favorite_books",
    setOf(
      org.jetbrains.compose.resources.ResourceItem(setOf(),
    "composeResources/cmp_bookpedia.composeapp.generated.resources/values/strings.commonMain.cvr",
    71, 81),
    )
)

internal val Res.string.no_search_results: StringResource
  get() = CommonMainString0.no_search_results

private fun init_no_search_results(): StringResource =
    org.jetbrains.compose.resources.StringResource(
  "string:no_search_results", "no_search_results",
    setOf(
      org.jetbrains.compose.resources.ResourceItem(setOf(),
    "composeResources/cmp_bookpedia.composeapp.generated.resources/values/strings.commonMain.cvr",
    153, 93),
    )
)

internal val Res.string.search_hint: StringResource
  get() = CommonMainString0.search_hint

private fun init_search_hint(): StringResource = org.jetbrains.compose.resources.StringResource(
  "string:search_hint", "search_hint",
    setOf(
      org.jetbrains.compose.resources.ResourceItem(setOf(),
    "composeResources/cmp_bookpedia.composeapp.generated.resources/values/strings.commonMain.cvr",
    247, 31),
    )
)

internal val Res.string.search_results: StringResource
  get() = CommonMainString0.search_results

private fun init_search_results(): StringResource = org.jetbrains.compose.resources.StringResource(
  "string:search_results", "search_results",
    setOf(
      org.jetbrains.compose.resources.ResourceItem(setOf(),
    "composeResources/cmp_bookpedia.composeapp.generated.resources/values/strings.commonMain.cvr",
    279, 42),
    )
)
