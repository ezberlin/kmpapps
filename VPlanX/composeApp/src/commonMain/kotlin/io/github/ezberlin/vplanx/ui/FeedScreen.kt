package io.github.ezberlin.vplanx.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import io.github.ezberlin.vplanx.data.ApiHandler
import io.github.ezberlin.vplanx.api.Secrets
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun FeedScreen() {
    LaunchedEffect(Unit) {
        val apiHandler = ApiHandler(Secrets.USERNAME, Secrets.PASSWORD)
        println(apiHandler.fetchTimetable())
    }

    MaterialTheme {
        var showContent by remember { mutableStateOf(false) }
        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Button(onClick = { showContent = !showContent }) {
                Text("Click me!")
            }
            AnimatedVisibility(showContent) {
                Text("Compose")
            }
        }
    }
}