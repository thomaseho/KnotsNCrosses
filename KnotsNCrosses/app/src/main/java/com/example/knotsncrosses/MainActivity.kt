package com.example.knotsncrosses

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.knotsncrosses.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    val TAG:String = "MainActivity"

    lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //GameManager.player = "Thomas"

        //GameManager.createGame(GameManager.player!!)

        //GameManager.currentGame?.let { GameManager.joinGame("kaare", "l20fo") }

        //val moves = listOf(listOf(1, 0, 0), listOf(0,0,0), listOf(0,0,0))
        //GameManager.updateGame("l20fo", moves)

        //GameManager.pollGame("l20fo")


    }
}