package io.github.ezberlin.bookpedia

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import io.github.ezberlin.bookpedia.book.presentation.book_list.BookListScreenRoot
import io.github.ezberlin.bookpedia.book.presentation.book_list.BookListViewModel

@Composable
fun App() {
    BookListScreenRoot(
        viewModel = remember { BookListViewModel() },
        onBookClick = {

        }
    )
}