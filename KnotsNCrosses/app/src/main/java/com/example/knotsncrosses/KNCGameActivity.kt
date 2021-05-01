package com.example.knotsncrosses

import android.annotation.SuppressLint
import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.knotsncrosses.databinding.ActivityKNCGameBinding

class KNCGameActivity : AppCompatActivity() {

    private lateinit var binding: ActivityKNCGameBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (GameHolder.PickedGame != null){

            binding = ActivityKNCGameBinding.inflate(layoutInflater)
            setContentView(binding.root)

            binding.playerOneName.text = GameHolder.PickedGame!!.players[0]

            if (GameHolder.PickedGame!!.players.size > 1){

                binding.playerTwoName.text = GameHolder.PickedGame!!.players[1]

            } else {

                binding.playerTwoName.text = "Waiting for second player"

            }



        }
    }
}