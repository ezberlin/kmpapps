import io.github.ezberlin.schachmeister.core.GameState
import io.github.ezberlin.schachmeister.core.MatchState
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class GameStateTest {
    @Test
    fun testAddPlayer() {
        val gameState = GameState()
        gameState.reset()
        gameState.addPlayer("Player1")
        val player = gameState.getPlayerByName("Player1")
        assertNotNull(player)
        assertEquals("Player1", player.playerName)
        println("Players after adding Player1: ${gameState.getAllPlayersSorted()}")
    }

    @Test
    fun testStartTournament() {
        val gameState = GameState()
        gameState.reset()
        gameState.addPlayer("Player1")
        gameState.addPlayer("Player2")
        gameState.startTournament()
        assertTrue(gameState.tournamentActive)
        println("Players after starting tournament: ${gameState.getAllPlayersSorted()}")
    }

    @Test
    fun testStartNextRound() {
        val gameState = GameState()
        gameState.reset()
        gameState.addPlayer("Player1")
        gameState.addPlayer("Player2")
        gameState.startTournament()
        val pairs = gameState.startNextRound()
        assertTrue(gameState.roundActive)
        assertEquals(1, pairs.size)
        val playerNames = pairs.flatMap { listOf(it.first.playerName, it.second.playerName) }
        assertTrue(playerNames.contains("Player1"))
        assertTrue(playerNames.contains("Player2"))
        println("Players after starting next round: ${gameState.getAllPlayersSorted()}")
    }

    @Test
    fun testChangeMatchState() {
        val gameState = GameState()
        gameState.reset()
        gameState.addPlayer("Player1")
        gameState.addPlayer("Player2")
        gameState.startTournament()
        gameState.startNextRound()
        gameState.changeMatchState("Player1", MatchState.WON)
        val player1 = gameState.getPlayerByName("Player1")
        val player2 = gameState.getPlayerByName("Player2")
        assertEquals(MatchState.WON, player1!!.currentMatchState)
        assertEquals(MatchState.LOST, player2!!.currentMatchState)
        println("Players after changing match state: ${gameState.getAllPlayersSorted()}")
    }

    @Test
    fun testEndCurrentRound() {
        val gameState = GameState()
        gameState.reset()
        gameState.addPlayer("Player1")
        gameState.addPlayer("Player2")
        gameState.startTournament()
        gameState.startNextRound()
        gameState.changeMatchState("Player1", MatchState.WON)
        gameState.endCurrentRound()
        val player1 = gameState.getPlayerByName("Player1")
        val player2 = gameState.getPlayerByName("Player2")
        assertEquals(1.0, player1!!.score)
        assertEquals(0.0, player2!!.score)
        assertTrue(!gameState.roundActive)
        println("Players after ending current round: ${gameState.getAllPlayersSorted()}")
    }

    @Test
    fun testActivatePlayer() {
        val gameState = GameState()
        gameState.reset()
        gameState.addPlayer("Player1")
        gameState.startTournament()
        gameState.deactivatePlayer("Player1")
        gameState.activatePlayer("Player1")
        val player = gameState.getPlayerByName("Player1")
        assertTrue(player!!.active)
        println("Players after activating Player1: ${gameState.getAllPlayersSorted()}")
    }

    @Test
    fun testDeactivatePlayer() {
        val gameState = GameState()
        gameState.reset()
        gameState.addPlayer("Player1")
        gameState.startTournament()
        gameState.deactivatePlayer("Player1")
        val player = gameState.getPlayerByName("Player1")
        assertTrue(!player!!.active)
        println("Players after deactivating Player1: ${gameState.getAllPlayersSorted()}")
    }

    @Test
    fun testRemovePlayer() {
        val gameState = GameState()
        gameState.reset()
        gameState.addPlayer("Player1")
        gameState.removePlayer("Player1")
        val player = gameState.getPlayerByName("Player1")
        assertEquals(null, player)
        println("Players after removing Player1: ${gameState.getAllPlayersSorted()}")
    }

    @Test
    fun testEndTournament() {
        val gameState = GameState()
        gameState.reset()
        gameState.addPlayer("Player1")
        gameState.addPlayer("Player2")
        gameState.startTournament()
        gameState.endTournament()
        assertTrue(!gameState.tournamentActive)
        assertTrue(!gameState.roundActive)
        assertEquals(null, gameState.getPlayerByName("Player1")!!.opponent)
        assertEquals(null, gameState.getPlayerByName("Player2")!!.opponent)
        println("Players after ending tournament: ${gameState.getAllPlayersSorted()}")
    }
}