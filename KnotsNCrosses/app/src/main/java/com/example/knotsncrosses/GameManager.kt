package com.example.knotsncrosses

import com.example.knotsncrosses.api.GameService
import com.example.knotsncrosses.api.data.Game
import com.example.knotsncrosses.api.data.GameState

object GameManager {

    lateinit var currentGames: MutableList<Game>

    var player:String? = null
    var state:GameState? = null

    val StartingGameState = listOf(listOf(0,0,0), listOf(0,0,0), listOf(0,0,0))

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
                }
            }
        }

    }

}