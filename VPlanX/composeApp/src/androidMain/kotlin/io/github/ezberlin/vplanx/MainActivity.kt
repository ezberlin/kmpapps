package io.github.ezberlin.vplanx

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import io.github.ezberlin.vplanx.navigation.AppNavHost
import io.github.ezberlin.vplanx.ui.FeedScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AppNavHost()
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    AppNavHost()
}