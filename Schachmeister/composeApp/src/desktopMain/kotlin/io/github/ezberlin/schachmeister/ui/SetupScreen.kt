package io.github.ezberlin.schachmeister.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import io.github.ezberlin.schachmeister.core.Player

@Composable
fun SetupScreen(
    players: List<Player>,
    onAddPlayer: (String) -> Unit,
    onRemovePlayer: (String) -> Unit,
    onDeactivatePlayer: (String) -> Unit,
    onActivatePlayer: (String) -> Unit,
    onStartTournament: () -> Unit
) {
    var newPlayerName by remember { mutableStateOf("") }

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

                    IconButton(
                        onClick = { onRemovePlayer(player.playerName) },
                        modifier = Modifier
                            .weight(0.1f)
                    ) {
                        Icon(Icons.Default.Delete, contentDescription = "Spieler löschen")
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            TextField(
                value = newPlayerName,
                onValueChange = { newPlayerName = it },
                label = { Text("Name") },
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = {
                if (newPlayerName.isNotBlank()) {
                    onAddPlayer(newPlayerName)
                    newPlayerName = ""
                }
            }) {
                Text("Spieler hinzufügen")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = onStartTournament) {
            Text("Turnier starten")
        }
    }
}