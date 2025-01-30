package io.github.ezberlin.schachmeister.ui

import androidx.compose.runtime.*
import io.github.ezberlin.schachmeister.core.GameState
import io.github.ezberlin.schachmeister.serialization.loadGameState
import io.github.ezberlin.schachmeister.serialization.saveGameState
import io.github.ezberlin.schachmeister.ui.theme.SchachmeisterTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

class GameStateHolder {
    var state by mutableStateOf(GameState())
        private set

    fun updateState(block: GameState.() -> Unit) {
        state = state.copy().apply(block)
    }
}
@Composable
@Preview
fun App() {
    val holder = remember { GameStateHolder() }

    var onWelcomeScreen by remember { mutableStateOf(true) }

    println(holder.state)

    SchachmeisterTheme {
        when {
            onWelcomeScreen -> {
                WelcomeScreen(
                    onButtonPress = {
                        onWelcomeScreen = !onWelcomeScreen
                    },
                    onLoadGameState = {
                        val loadedState = loadGameState("GAME_STATE_FILE_PATH")
                        holder.updateState {
                            setState(loadedState)
                        }
                    }
                )
            }

            !holder.state.tournamentActive -> {
                SetupScreen(
                    players = holder.state.getAllPlayersSorted(),
                    onAddPlayer = { playerName ->
                        holder.updateState {
                            addPlayer(playerName)
                        }
                    },
                    onRemovePlayer = { playerName ->
                        holder.updateState {
                            removePlayer(playerName)
                        }
                    },
                    onStartTournament = {
                        holder.updateState {
                            startTournament()
                        }
                    },
                    onActivatePlayer = { playerName ->
                        holder.updateState {
                            activatePlayer(playerName)
                        }
                    },
                    onDeactivatePlayer = { playerName ->
                        holder.updateState {
                            deactivatePlayer(playerName)
                        }
                    }
                )
            }

            holder.state.roundActive -> {
                RoundScreen(
                    matches = holder.state.getAllMatches(),
                    onEndRound = {
                        holder.updateState {
                            endCurrentRound()
                        }
                    },
                    onSetMatchResult = { playerName, newMatchState ->
                        holder.updateState {
                            changeMatchState(playerName, newMatchState)
                        }
                    }
                )
            }

            else -> {
                TournamentScreen(
                    players = holder.state.getAllPlayersSorted(),
                    onActivatePlayer = { playerName ->
                        holder.updateState {
                            activatePlayer(playerName)
                        }
                    },
                    onDeactivatePlayer = { playerName ->
                        holder.updateState {
                            deactivatePlayer(playerName)
                        }
                    },
                    onRoundStart = {
                        holder.updateState {
                            startNextRound()
                        }
                    },
                    onSaveGameState = {
                        saveGameState(holder.state, "GAME_STATE_FILE_PATH")
                    }
                )
            }
        }
    }
}