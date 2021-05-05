package com.example.knotsncrosses

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.example.knotsncrosses.api.data.Game
import com.example.knotsncrosses.api.data.GameState
import com.example.knotsncrosses.databinding.ActivityKNCGameBinding
import kotlinx.android.synthetic.main.activity_k_n_c_game.*

class KNCGameActivity : AppCompatActivity() {

    private lateinit var binding: ActivityKNCGameBinding

    val TAG: String = "GameActivity"

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (GameHolder.PickedGame != null){

            binding = ActivityKNCGameBinding.inflate(layoutInflater)
            setContentView(binding.root)

            val grid = listOf(
                    r0b0, r0b1, r0b2,
                    r1b0, r1b1, r1b2,
                    r2b0, r2b1, r2b2
            )

            binding.playerOneName.text = GameHolder.PickedGame!!.players[0]

            if (GameHolder.PickedGame!!.players.size > 1){

                binding.playerTwoName.text = GameHolder.PickedGame!!.players[1]

            } else {

                binding.playerTwoName.text = "Waiting for second player"

            }

            setupGrid(grid)

            GameManager.onGameActivty = {

                setupGrid(grid)
                whoIsUp(it)
                checkForWinner(it)

            }


            r0b0.setOnClickListener {

                if (binding.r0b0.text == "") {

                    binding.r0b0.text = getToken()
                    makeMove(0,0)

                } else {
                    spotTaken()
                }
            }

            r0b1.setOnClickListener {

                if (binding.r0b1.text == "") {

                    binding.r0b1.text = getToken()
                    makeMove(0,1)

                } else {
                    spotTaken()
                }
            }

            r0b2.setOnClickListener {

                if (binding.r0b2.text == "") {

                    binding.r0b2.text = getToken()
                    makeMove(0,2)

                } else {
                    spotTaken()
                }
            }

            r1b0.setOnClickListener {

                if (binding.r1b0.text == "") {

                    binding.r1b0.text = getToken()
                    makeMove(1,0)

                } else {
                    spotTaken()
                }
            }

            r1b1.setOnClickListener {

                if (binding.r1b1.text == "") {

                    binding.r1b1.text = getToken()
                    makeMove(1,1)

                } else {
                    spotTaken()
                }
            }

            r1b2.setOnClickListener {

                if (binding.r1b2.text == "") {

                    binding.r1b2.text = getToken()
                    makeMove(1,2)

                } else {
                    spotTaken()
                }
            }

            r2b0.setOnClickListener {

                if (binding.r2b0.text == "") {

                    binding.r2b0.text = getToken()
                    makeMove(2,0)

                } else {
                    spotTaken()
                }
            }

            r2b1.setOnClickListener {

                if (binding.r2b1.text == "") {

                    binding.r2b1.text = getToken()
                    makeMove(2,1)

                } else {
                    spotTaken()
                }
            }

            r2b2.setOnClickListener {

                if (binding.r2b2.text == "") {

                    binding.r2b2.text = getToken()
                    makeMove(2,2)

                } else {
                    spotTaken()
                }
            }
        }
    }

    private fun spotTaken() {
        Toast.makeText(this, "That spot is taken kid", Toast.LENGTH_SHORT).show()
    }

    private fun getToken(): String{

        val token: String

        if (GameHolder.PickedGame!!.players[0] == GameManager.player){

            token = "X"

        } else {

            token = "O"

        }

        return token
    }

    private fun makeMove(row: Int, spot: Int){

        val player: Int

        if (GameHolder.PickedGame!!.players[0] == GameManager.player){
            player = 1
        } else {
            player = 2
        }

        GameManager.putMove(player, row, spot)

    }

    private fun updateGridButton(state: Int, gridbutton: Button){

        when (state) {

            0 -> gridbutton.text = ""
            1 -> gridbutton.text = "X"
            2 -> gridbutton.text = "O"

            else -> {
                Log.d(TAG,"Something is not quite right")
            }
        }
    }

    private fun setupGrid(grid: List<Button>){

        val ints = listOf(0, 1, 2, 3, 4, 5, 6, 7, 8)


        val states: List<Int> = listOf(
                GameHolder.PickedGame!!.state[0][0],
                GameHolder.PickedGame!!.state[0][1],
                GameHolder.PickedGame!!.state[0][2],
                GameHolder.PickedGame!!.state[1][0],
                GameHolder.PickedGame!!.state[1][1],
                GameHolder.PickedGame!!.state[1][2],
                GameHolder.PickedGame!!.state[2][0],
                GameHolder.PickedGame!!.state[2][1],
                GameHolder.PickedGame!!.state[2][2]
        )

        for (i in ints){

            updateGridButton(states[i], grid[i])

        }

    }

    private fun checkForWinner(game: Game) {

        val winner = GameManager.checkForWin(game.state)

        if (winner != 0){
            displayWinner(winner)
        }

    }

    @SuppressLint("SetTextI18n")
    private fun displayWinner(player: Int){

        binding.winnerText.text = "${GameHolder.PickedGame!!.players[player - 1]} Wins!"
        binding.winnerText.setTextColor(Color.parseColor("#0aad3f"))

        freezeGrid()

    }

    @SuppressLint("SetTextI18n")
    private fun whoIsUp(game: Game){

        val whoseTurn = GameManager.whoseTurn()

        if (whoseTurn == game.players[0]) {

            if (game.players.size > 1) {

                binding.playerTwoName.text = GameHolder.PickedGame!!.players[1]
                binding.cheaterText.text = "${GameHolder.PickedGame!!.players[0]} is up"

            }

            if (whoseTurn == GameManager.cheater){

                binding.cheaterText.text = GameManager.cheater

            }

        } else {

            if (game.players.size > 1) {

                binding.cheaterText.text = "${GameHolder.PickedGame!!.players[1]} is up"

            }

        }

        if (whoseTurn == GameManager.player) {

            unFreezeGrid()

        } else {

            freezeGrid()

        }

    }

    private fun unFreezeGrid() {

        binding.r0b0.isClickable = true
        binding.r0b1.isClickable = true
        binding.r0b2.isClickable = true
        binding.r1b0.isClickable = true
        binding.r1b1.isClickable = true
        binding.r1b2.isClickable = true
        binding.r2b0.isClickable = true
        binding.r2b1.isClickable = true
        binding.r2b2.isClickable = true

    }

    private fun freezeGrid(){

        binding.r0b0.isClickable = false
        binding.r0b1.isClickable = false
        binding.r0b2.isClickable = false
        binding.r1b0.isClickable = false
        binding.r1b1.isClickable = false
        binding.r1b2.isClickable = false
        binding.r2b0.isClickable = false
        binding.r2b1.isClickable = false
        binding.r2b2.isClickable = false

    }
}