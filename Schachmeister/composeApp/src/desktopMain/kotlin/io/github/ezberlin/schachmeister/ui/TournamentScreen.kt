package io.github.ezberlin.schachmeister.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import io.github.ezberlin.schachmeister.core.Player

@Composable
fun TournamentScreen(
    players: List<Player>,
    onDeactivatePlayer: (String) -> Unit,
    onActivatePlayer: (String) -> Unit,
    onRoundStart: () -> Unit,
    onSaveGameState: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
    ) {
        LazyColumn {
            items(
                items = players,
                key = { it.playerName }
            ) { player ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Surface(
                        modifier = Modifier
                            .weight(0.9f)
                            .clickable {
                                if (player.active)
                                    onDeactivatePlayer(player.playerName)
                                else
                                    onActivatePlayer(player.playerName)
                            },
                        color = if (player.active)
                            Color.White
                        else
                            Color.LightGray
                    ) {
                        Text(
                            text = player.playerName,
                            textAlign = TextAlign.Center,
                        )
                    }

                    Text(
                        text = player.score.toString(),
                        modifier = Modifier
                            .weight(0.1f)
                            .padding(8.dp),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }

        Button(
            onClick = onRoundStart,
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Green),
            shape = CircleShape,
        ) {
            Icon(
                imageVector = Icons.Default.PlayArrow,
                contentDescription = "Runde starten!",
                tint = Color.White
            )
        }

        Button(
            onClick = onSaveGameState,
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Blue),
            shape = CircleShape,
        ) {
            Icon(
                imageVector = Icons.Default.Create,
                contentDescription = "Speichern!",
                tint = Color.White
            )
        }
    }
}