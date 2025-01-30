package io.github.ezberlin.schachmeister.core

enum class MatchState(
    val scoreOnFinish: Double?,
    val opposingStateOrdinal: Int
) {

    ONGOING(null, 0),
    WON(1.0, 3),
    TIED(0.5, 2),
    LOST(0.0, 1);
}