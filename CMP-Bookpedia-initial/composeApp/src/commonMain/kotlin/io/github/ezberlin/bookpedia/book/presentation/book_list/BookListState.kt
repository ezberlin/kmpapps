package io.github.ezberlin.bookpedia.book.presentation.book_list

import io.github.ezberlin.bookpedia.book.domain.Book
import io.github.ezberlin.bookpedia.core.presentation.UiText

data class BookListState(
    val searchQuery: String = "Kotlin",
    val searchResults: List<Book> = books,
    val favoriteBooks: List<Book> = emptyList(),
    val isLoading: Boolean = false,
    val selectedTabIndex: Int = 0,
    val errorMessage: UiText? = null
)

val books = (1..100).map {
    Book(
        id = it.toString(),
        title = "Book $it",
        imageUrl = "https://lol.com",
        authors = listOf("ezberlin"),
        description = "Description $it",
        languages = emptyList(),
        firstPublishedYear = null,
        averageRating = 4.31415,
        ratingCount = 42,
        numPages = 100,
        numEditions = 3
    )
}