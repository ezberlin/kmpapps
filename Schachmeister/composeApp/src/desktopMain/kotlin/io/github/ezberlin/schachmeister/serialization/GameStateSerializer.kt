package io.github.ezberlin.schachmeister.serialization

import io.github.ezberlin.schachmeister.core.GameState
import kotlinx.serialization.json.Json
import java.io.File

fun saveGameState(gameState: GameState, filePath: String) {
    val jsonString = Json.encodeToString(gameState)
    File(filePath).writeText(jsonString)
}

fun loadGameState(filePath: String): GameState {
    val jsonString = File(filePath).readText()
    return Json.decodeFromString(jsonString) as GameState
}