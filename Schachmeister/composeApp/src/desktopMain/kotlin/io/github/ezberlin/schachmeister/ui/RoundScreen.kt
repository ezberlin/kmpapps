package io.github.ezberlin.schachmeister.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import io.github.ezberlin.schachmeister.core.MatchState
import io.github.ezberlin.schachmeister.core.Player

@Composable
fun RoundScreen(
    matches: List<Pair<Player, Player>>,
    onEndRound: () -> Unit,
    onSetMatchResult: (String, MatchState) -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Button(
            onClick = { onEndRound() },
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red),
            shape = CircleShape,
        ) {
            Text(
                text = "End Round",
                color = Color.White
            )
        }

        LazyColumn {
            items(
                items = matches,
                key = { it.first.playerName }
            ) { (player1, player2) ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "${player1.playerName} vs ${player2.playerName}",
                        style = MaterialTheme.typography.h6,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        PlayerCard(
                            player = player1,
                            modifier = Modifier.weight(0.5f)
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        PlayerCard(
                            player = player2,
                            modifier = Modifier.weight(0.5f)
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Button(
                            onClick = { onSetMatchResult(player1.playerName, MatchState.WON) },
                            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Green),
                            shape = CircleShape,
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(
                                text = "${player1.playerName} Wins",
                                color = Color.White
                            )
                        }

                        Button(
                            onClick = { onSetMatchResult(player1.playerName, MatchState.TIED) },
                            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Gray),
                            shape = CircleShape,
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(
                                text = "Tie",
                                color = Color.White
                            )
                        }

                        Button(
                            onClick = { onSetMatchResult(player2.playerName, MatchState.WON) },
                            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red),
                            shape = CircleShape,
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(
                                text = "${player2.playerName} Wins",
                                color = Color.White
                            )
                        }
                    }
                }
            }
        }


    }
}

@Composable
fun PlayerCard(player: Player, modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth(),
            color = Color.LightGray
        ) {
            Text(
                text = player.playerName,
                textAlign = TextAlign.Center,
            )
        }

        Text(
            text = player.score.toString(),
            modifier = Modifier
                .padding(8.dp),
            textAlign = TextAlign.Center
        )
    }
}