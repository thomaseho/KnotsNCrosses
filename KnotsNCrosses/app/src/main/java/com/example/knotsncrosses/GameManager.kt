package com.example.knotsncrosses

import android.util.Log
import android.widget.Toast
import com.example.knotsncrosses.api.GameService
import com.example.knotsncrosses.api.data.Game
import com.example.knotsncrosses.api.data.GameState

object GameManager {

    lateinit var currentGames: MutableList<Game>

    var recentGame: Game = Game(mutableListOf(), "", mutableListOf())

    var player:String? = null
    var state:GameState? = null
    val StartingGameState = mutableListOf(mutableListOf(0,0,0), mutableListOf(0,0,0), mutableListOf(0,0,0))
    val cheater: String = "Wait for another player..."

    var onGameActivty:((Game) -> Unit)? = null
    var onRecentGame:((Game) -> Unit)? = null
    var onCurrentGames:((List<Game>) -> Unit)? = null
    var onChanges:((List<Game>) -> Unit)? = null

    val TAG:String = "GameManager"


    fun createGame(player:String){

        GameService.createGame(player, StartingGameState) { game: Game?, err: Int? ->
            if (err != null){
                if (err == 406){

                    Log.d(TAG, "Something is wrong with the header for the request")

                }
                else {

                    Log.d(TAG, "Something is wrong the request. Error code: ${err}")

                }
            }
            else {
                if (game != null) {

                    Log.d(TAG, "You created a game with id ${game.gameId}")

                    currentGames.add(game)
                    recentGame = game
                    updateCurrentGames()
                    updateRecentGame()
                    
                }
            }
        }
    }

    fun joinGame(player: String, gameId: String) {

        GameService.joinGame(player, gameId) { game: Game?, err: Int? ->

            if(err != null){
                if (err == 406){

                    Log.d(TAG, "Something is wrong with the header for the request")

                }
                else {

                    Log.d(TAG, "Something is wrong the request. Error code: ${err}")

                }
            }
            else {
                if (game != null){

                    Log.d(TAG, "You joined the game with id ${game.gameId}")

                    currentGames.add(game)
                    recentGame = game
                    updateCurrentGames()

                }
            }
        }
    }

    fun updateGame(gameId: String, gameState: GameState) {

        GameService.updateGame(gameId, gameState) { game: Game?, err: Int? ->

            if (err != null) {
                if (err == 406) {

                    Log.d(TAG, "Something is wrong with the header for the request")

                } else {

                    Log.d(TAG, "Something is wrong the request. Error code: ${err}")

                }
            } else {
                if (game != null) {

                    Log.d(TAG, "You updated the game with id ${game.gameId}")

                }
            }
        }
    }

    fun pollGame(gameId: String) {

        GameService.pollGame(gameId) { game: Game?, err: Int? ->

            if (err != null) {
                if (err == 406) {

                    Log.d(TAG, "Something is wrong with the header for the request")

                } else {

                    Log.d(TAG, "Something is wrong the request. Error code: ${err}")

                }
            } else {
                if (game != null) {

                    Log.d(TAG, "You polled a game with id ${game.gameId}")

                    currentGames.forEach{

                        if (it.gameId == game.gameId){
                            if (it.state != game.state){

                                it.state = game.state

                            }
                            if (it.players != game.players){

                                it.players = game.players

                            }
                        }

                        if (game == GameHolder.PickedGame) {

                            updatePickedGame()

                        }
                    }
                }
            }
        }

    }

    fun checkForWin(gameState: GameState): Int{


        if (gameState[0][0] == gameState[0][1] && gameState[0][0] == gameState[0][2] && gameState[0][0] != 0){
            if (gameState[0][0] == 1){

                return 1

            } else {

                return 2
            }
        }

        if (gameState[1][0] == gameState[1][1] && gameState[1][0] == gameState[1][2] && gameState[1][0] != 0){
            if (gameState[1][0] == 1){

                return 1

            } else {

                return 2
            }
        }

        if (gameState[2][0] == gameState[2][1] && gameState[2][0] == gameState[2][2] && gameState[2][0] != 0){
            if (gameState[2][0] == 1){

                return 1

            } else {

                return 2
            }
        }

        if (gameState[0][0] == gameState[1][0] && gameState[0][0] == gameState[2][0] && gameState[0][0] != 0){
            if (gameState[0][0] == 1){

                return 1

            } else {

                return 2
            }
        }

        if (gameState[0][1] == gameState[1][1] && gameState[0][1] == gameState[2][1] && gameState[0][1] != 0){
            if (gameState[0][1] == 1){

                return 1

            } else {

                return 2
            }
        }

        if (gameState[0][2] == gameState[1][2] && gameState[0][2] == gameState[2][2] && gameState[0][2] != 0){
            if (gameState[0][2] == 1){

                return 1

            } else {

                return 2
            }
        }

        if (gameState[0][0] == gameState[1][1] && gameState[0][0] == gameState[2][2] && gameState[0][0] != 0){
            if (gameState[0][0] == 1){

                return 1

            } else {

                return 2
            }
        }

        if (gameState[0][2] == gameState[1][1] && gameState[0][2] == gameState[2][0] && gameState[0][2] != 0){
            if (gameState[0][2] == 1){

                return 1

            } else {

                return 2
            }
        }

        return 0
    }

    fun putMove(token: Int, row: Int, spot: Int){

        GameHolder.PickedGame!!.state[row].set(spot, token)

        updateGame(GameHolder.PickedGame!!.gameId, GameHolder.PickedGame!!.state)

    }

    fun updateCurrentGames(){

        onCurrentGames?.invoke(currentGames)
        updateChanges()

    }

    fun updateRecentGame(){

        onRecentGame?.invoke(recentGame)

    }

    fun updatePickedGame(){

        onGameActivty?.invoke(GameHolder.PickedGame!!)

    }

    fun updateChanges(){

        onChanges?.invoke(currentGames)

    }

    fun loadGames() {

        currentGames = mutableListOf()

    }

    fun whoseTurn(): String {

        val game = GameHolder.PickedGame!!
        var xCount: Int = 0
        var oCount: Int = 0

        game.state.forEach {
            it.forEach {

                when(it) {

                    1 -> xCount += 1
                    2 -> oCount += 1

                }

            }
        }

        if (xCount <= oCount) {

            return game.players[0]

        } else {

            if (game.players.size > 1) {

                return game.players[1]

            } else {

                return cheater

            }

        }

    }

}