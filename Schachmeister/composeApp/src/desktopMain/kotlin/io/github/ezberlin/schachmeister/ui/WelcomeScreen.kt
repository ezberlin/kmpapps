package io.github.ezberlin.schachmeister.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun WelcomeScreen(
    onButtonPress: () -> Unit,
    onLoadGameState: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Spacer(Modifier.height(64.dp))

        Text(
            text = "Schachmeister",
            style = MaterialTheme.typography.h1
        )

        Spacer(Modifier.height(64.dp))

        Row(
            modifier = Modifier
                .weight(1f)
        ) {
            Button(
                onClick = onButtonPress,
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.DarkGray),
                shape = CircleShape
            ) {
                Icon(
                    imageVector = Icons.Default.PlayArrow,
                    contentDescription = "Setup starten!",
                    tint = Color.White,
                    modifier = Modifier
                        .fillMaxSize(0.5f)
                )
            }

            Button(
                onClick = onLoadGameState,
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Yellow),
                shape = CircleShape
            ) {
                Icon(
                    imageVector = Icons.Default.Build,
                    contentDescription = "Setup starten!",
                    tint = Color.White,
                    modifier = Modifier
                        .fillMaxSize(0.5f)
                )
            }
        }



        Spacer(Modifier.height(64.dp))

        Text(
            text = "Gemacht mit \uD83E\uDEDA von Ismail",
            style = MaterialTheme.typography.h2
        )

        Spacer(Modifier.height(64.dp))
    }
}
