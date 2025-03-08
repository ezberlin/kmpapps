package io.github.ezberlin.vplanx

import androidx.compose.ui.window.ComposeUIViewController
import io.github.ezberlin.vplanx.navigation.AppNavHost

fun MainViewController() = ComposeUIViewController { AppNavHost() }