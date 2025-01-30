package io.github.ezberlin.schachmeister.core

import kotlinx.serialization.Serializable

@Serializable
data class GameState(
    // Zurzeitige Spieler mit Name, Punktzahl, Gegner und Zufallsscore
    private var players: List<Player> = emptyList(),

    // Turnier aktiv, d.h. Spielerdaten werden nicht mehr geändert, Zufallsscores können gegeben werden
    var tournamentActive: Boolean = false,

    // Runde aktiv, d.h. Gegner sind festgelegt
    var roundActive: Boolean = false,

    private var nextRound: Int? = null,
) {
    fun reset() {
        players = emptyList()

        tournamentActive = false
        roundActive = false
        nextRound = null
    }

    fun setState(gameState: GameState) {
        players = gameState.players
        tournamentActive = gameState.tournamentActive
        roundActive = gameState.roundActive
        nextRound = gameState.nextRound
    }

    private fun assignRandomRankings() {
        assert(tournamentActive)

        val rankings = (0..players.size).toList().shuffled()
        players = players.mapIndexed { i, player ->
            player.copy(randomRanking = rankings[i])
        }
    }

    fun startTournament() {
        nextRound = 1

        tournamentActive = true
        assignRandomRankings()
    }

    fun endTournament() {
        assert(tournamentActive)
        assert(!roundActive)

        tournamentActive = false
    }

    fun startNextRound(): List<Pair<Player, Player>> {
        assert(tournamentActive)
        assert(!roundActive)

        val playersToMatch = players
            .filter { it.active }
            .sortedWith(
                compareByDescending<Player> { it.score }
                    .thenByDescending { it.randomRanking }
            )
            .chunked(2)
            .mapNotNull {
                if (it.size == 2) it[0] to it[1] else null
            }

        playersToMatch.forEach {
            startMatch(it.first.playerName, it.second.playerName)
        }

        roundActive = true
        return playersToMatch
    }

    fun changeMatchState(name: String, newState: MatchState) {
        assert(tournamentActive)
        assert(roundActive)

        val changedPlayer = players.findLast { it.playerName == name }!!
        changedPlayer.currentMatchState = newState

        val opponentPlayer = players.findLast { changedPlayer.opponent!!.playerName == it.playerName }
        opponentPlayer?.currentMatchState = MatchState.entries[newState.opposingStateOrdinal]

        players = players.toList()
    }

    fun endCurrentRound() {
        assert(tournamentActive)
        assert(roundActive)

        assignRandomRankings()
        players = players
            .sortedByDescending { it.randomRanking }
            .map {
                if (it.active && it.opponent?.active == true) it.copy(
                    score = it.score + it.currentMatchState!!.scoreOnFinish!!,
                    opponent = null,
                    currentMatchState = null
                ) else it
            }
            .toList()

        roundActive = false
        nextRound = nextRound!! + 1
    }

    fun addPlayer(name: String) {
        assert(!tournamentActive)

        players += Player(name, 0.0, null, 0, true, null)
    }

    fun getPlayerByName(name: String): Player? = players.findLast { it.playerName == name }

    fun getAllPlayersSorted(): List<Player> = players
        .sortedWith(
            compareByDescending<Player> { it.score }
                .thenByDescending { it.randomRanking }
                .thenBy { !it.active }
        )

    fun getAllMatches(): List<Pair<Player, Player>> {
        val seenPairs = mutableSetOf<Set<Player>>()
        return players
            .filter {
                it.opponent != null
                        && it.active
                        && it.opponent!!.active
                        && it.currentMatchState == MatchState.ONGOING
            }
            .mapNotNull { player ->
                val pair = setOf(player, player.opponent!!)
                if (seenPairs.add(pair)) {
                    player to player.opponent!!
                } else {
                    null
                }
            }
    }

    fun activatePlayer(name: String) {
        assert(tournamentActive)

        players = players.map {
            if (it.playerName == name) it.copy(active = true) else it
        }
    }

    fun deactivatePlayer(name: String) {
        assert(tournamentActive)

        players = players.map {
            if (it.playerName == name) it.copy(active = false) else it
        }
    }

    fun removePlayer(name: String) {
        assert(!tournamentActive)

        players = players
            .toMutableList()
            .apply {
                removeAll { it.playerName == name }
            }
            .toList()
    }

    private fun startMatch(name1: String, name2: String) {
        assert(tournamentActive)
        assert(!roundActive)

        players = players
            .toMutableList()
            .apply {
                findLast { it.playerName == name1 }!!
                    .also { player1 ->
                        player1.opponent = findLast { it.playerName == name2 }
                        player1.currentMatchState = MatchState.ONGOING
                    }
                findLast { it.playerName == name2 }!!
                    .also { player2 ->
                        player2.opponent = findLast { it.playerName == name1 }
                        player2.currentMatchState = MatchState.ONGOING
                    }
            }
            .toList()
    }
}