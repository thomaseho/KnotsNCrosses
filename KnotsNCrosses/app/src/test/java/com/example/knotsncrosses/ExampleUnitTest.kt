package com.example.knotsncrosses

import com.example.knotsncrosses.api.GameService
import com.example.knotsncrosses.api.GameServiceCallback
import com.example.knotsncrosses.api.data.Game
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

   /* var gameState:Game? = null
    val firstPlayer:String = "Thomas"
    val secondPlayer:String = "Thomas"
    val initState = listOf(listOf(0,0,0), listOf(0,0,0), listOf(0,0,0))

    @Test
    fun createGame(){

        GameService.createGame(firstPlayer, initState){ state:Game?, err: Int? ->

            gameState = state
            assertNotNull(state)
            assertNotNull(state?.gameId)
            assertEquals(firstPlayer, state?.players?.get(0))
        }

    } */


    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }
}