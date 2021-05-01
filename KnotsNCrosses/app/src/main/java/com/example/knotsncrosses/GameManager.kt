package com.example.knotsncrosses

import com.example.knotsncrosses.api.GameService
import com.example.knotsncrosses.api.data.Game
import com.example.knotsncrosses.api.data.GameState

object GameManager {

    lateinit var currentGames: MutableList<Game>

    var recentGame: Game = Game(mutableListOf(), "", mutableListOf())

    var player:String? = null
    var state:GameState? = null
    val StartingGameState = mutableListOf(mutableListOf(0,0,0), mutableListOf(0,0,0), mutableListOf(0,0,0))

    var onGameActivty:((Game) -> Unit)? = null
    var onRecentGame:((Game) -> Unit)? = null
    var onCurrentGames:((List<Game>) -> Unit)? = null
    var onChanges:((List<Game>) -> Unit)? = null


    fun createGame(player:String){

        GameService.createGame(player, StartingGameState) { game: Game?, err: Int? ->
            if (err != null){
                if (err == 406){

                    println("You dun goofed the header m8")

                }
                else {

                    println("Something dun goofed $err")

                }
            }
            else {
                if (game != null) {

                    println("You created a game with id ${game.gameId}")

                    currentGames.add(game)
                    recentGame = game
                    onCurrentGames?.invoke(currentGames)
                    updateRecentGame()
                }
            }
        }
    }

    fun joinGame(player: String, gameId: String) {

        GameService.joinGame(player, gameId) { game: Game?, err: Int? ->

            if(err != null){
                if (err == 406){

                    println("You dun goofed the header m8")

                }
                else {

                    println("Something dun goofed $err")

                }
            }
            else {
                if (game != null){

                    println("You joined a game with id ${game.gameId}")

                }
            }
        }
    }

    fun updateGame(gameId: String, gameState: GameState) {

        GameService.updateGame(gameId, gameState) { game: Game?, err: Int? ->

            if (err != null) {
                if (err == 406) {

                    println("You dun goofed the header m8")

                } else {

                    println("Something dun goofed $err")

                }
            } else {
                if (game != null) {

                    println("You updated a game with id ${game.gameId}")

                }
            }
        }
    }

    fun pollGame(gameId: String) {

        GameService.pollGame(gameId) { game: Game?, err: Int? ->

            if (err != null) {
                if (err == 406) {

                    println("You dun goofed the header m8")

                } else {

                    println("Something dun goofed $err")

                }
            } else {
                if (game != null) {

                    println("You polled a game with id ${game.gameId}")
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

}