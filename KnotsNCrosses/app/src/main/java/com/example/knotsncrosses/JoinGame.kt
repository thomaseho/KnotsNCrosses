package com.example.knotsncrosses

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.knotsncrosses.databinding.ActivityJoinGameBinding
import kotlinx.android.synthetic.main.activity_menu_screen.*

class JoinGame : AppCompatActivity() {

    private lateinit var binding: ActivityJoinGameBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityJoinGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        joinGameButton.setOnClickListener {

            joinGame()

        }
    }


    private fun joinGame(){

        val gameId = binding.joinGameId.text.toString()

        if (gameId.isNotEmpty()){

            GameManager.joinGame(GameManager.player.toString(), gameId)

        } else {

            Toast.makeText(this, "Real gamers join games with an ID, get one and come back", Toast.LENGTH_SHORT).show()

        }

        finish()

    }

}