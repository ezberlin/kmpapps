package io.github.ezberlin.schachmeister

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import io.github.ezberlin.schachmeister.ui.App

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Schachmeister",
    ) {
        App()
    }
}