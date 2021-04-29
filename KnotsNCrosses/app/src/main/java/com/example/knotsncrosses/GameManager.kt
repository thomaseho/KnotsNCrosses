package com.example.knotsncrosses

import com.example.knotsncrosses.api.GameService
import com.example.knotsncrosses.api.data.Game
import com.example.knotsncrosses.api.data.GameState

object GameManager {

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
                    println("Something dun goofed ${err}")
                }
            }
            else {
                println("You created a game dude")
            }
        }

    }

}