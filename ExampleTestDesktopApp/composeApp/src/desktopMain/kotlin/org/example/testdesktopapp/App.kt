package org.example.testdesktopapp

import Notify
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.rememberNotification
import androidx.compose.ui.window.rememberTrayState
import createNotification
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

import exampletestdesktopapp.composeapp.generated.resources.Res
import exampletestdesktopapp.composeapp.generated.resources.compose_multiplatform
import java.awt.SystemTray
import java.awt.Toolkit
import java.awt.TrayIcon

@Composable
@Preview
fun App() {
    MaterialTheme {
        var showContent by remember { mutableStateOf(false) }
        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Button(onClick = {
                showContent = !showContent
                val tray = SystemTray.getSystemTray()
                val image = Toolkit.getDefaultToolkit().createImage("logo.webp")
                val trayIcon = TrayIcon(image, "Desktop Notification")
                tray.add(trayIcon)
                trayIcon.displayMessage("Desktop Notification", "uwu", TrayIcon.MessageType.INFO)
            }) {
                Text("Click me!")
            }
            AnimatedVisibility(showContent) {
                val greeting = remember { Greeting().greet() }
                Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(painterResource(Res.drawable.compose_multiplatform), null)
                    Text("Compose: $greeting")
                }
            }
        }
    }
}