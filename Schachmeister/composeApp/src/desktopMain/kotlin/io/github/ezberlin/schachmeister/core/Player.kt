package io.github.ezberlin.schachmeister.core

import kotlinx.serialization.Serializable

@Serializable
data class Player(
    val playerName: String,
    var score: Double,
    var opponent: Player?,
    var randomRanking: Int,
    var active: Boolean,
    var currentMatchState: MatchState?
) {
    override fun toString(): String {
        return "Player(playerName='$playerName', score=$score, randomRanking=$randomRanking, active=$active, currentMatchState=$currentMatchState)"
    }

    override fun hashCode(): Int {
        return (playerName.hashCode() * 31 + score * 31 + randomRanking * 31).toInt()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Player

        if (score != other.score) return false
        if (randomRanking != other.randomRanking) return false
        if (active != other.active) return false
        if (playerName != other.playerName) return false
        if (opponent != other.opponent) return false
        if (currentMatchState != other.currentMatchState) return false

        return true
    }
}
