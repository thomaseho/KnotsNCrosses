package com.example.knotsncrosses

import com.example.knotsncrosses.api.GameService
import com.example.knotsncrosses.api.data.Game
import com.example.knotsncrosses.api.data.GameState

object GameManager {

    lateinit var currentGames: MutableList<Game>

    var recentGame: Game = Game(mutableListOf(), "", listOf())

    var player:String? = null
    var state:GameState? = null
    val StartingGameState = listOf(listOf(0,0,0), listOf(0,0,0), listOf(0,0,0))

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
                    }
                }
            }
        }

    }

    fun updateCurrentGames(){

        onCurrentGames?.invoke(currentGames)
        updateChanges()

    }

    fun updateRecentGame(){

        onRecentGame?.invoke(recentGame)

    }

    fun updateChanges(){

        onChanges?.invoke(currentGames)

    }

    fun loadGames(){

        currentGames = mutableListOf()

    }

}