package com.example.knotsncrosses

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.knotsncrosses.api.data.GameState
import com.example.knotsncrosses.databinding.ActivityKNCGameBinding
import kotlinx.android.synthetic.main.activity_k_n_c_game.*

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

            setupGrid()

            GameManager.onGameActivty = {

                setupGrid()
                val winner = GameManager.checkForWin(it.state)

                if (winner != 0){
                    displayWinner(winner)
                }

                if (it.players.size > 1) {

                    binding.playerTwoName.text = GameHolder.PickedGame!!.players[1]

                }

            }

            r0b0.setOnClickListener {

                if (binding.r0b0.text == "") {

                    val token = setToken(0, 0)

                    if (token == 1){
                        binding.r0b0.text = "X"
                    } else {
                        binding.r0b0.text = "O"
                    }

                }
                 else {
                    Toast.makeText(this, "That spot is taken kid", Toast.LENGTH_SHORT).show()
                }
            }

            r0b1.setOnClickListener {

                if (binding.r0b1.text == "") {

                    val token = setToken(0, 1)

                    if (token == 1){
                        binding.r0b1.text = "X"
                    } else {
                        binding.r0b1.text = "O"
                    }

                }
                else {
                    Toast.makeText(this, "That spot is taken kid", Toast.LENGTH_SHORT).show()
                }
            }

            r0b2.setOnClickListener {

                if (binding.r0b2.text == "") {

                    val token = setToken(0, 2)

                    if (token == 1){
                        binding.r0b2.text = "X"
                    } else {
                        binding.r0b2.text = "O"
                    }

                }
                else {
                    Toast.makeText(this, "That spot is taken kid", Toast.LENGTH_SHORT).show()
                }
            }

            r1b0.setOnClickListener {

                if (binding.r1b0.text == "") {

                    val token = setToken(1, 0)

                    if (token == 1){
                        binding.r1b0.text = "X"
                    } else {
                        binding.r1b0.text = "O"
                    }

                }
                else {
                    Toast.makeText(this, "That spot is taken kid", Toast.LENGTH_SHORT).show()
                }
            }

            r1b1.setOnClickListener {

                if (binding.r1b1.text == "") {

                    val token = setToken(1, 1)

                    if (token == 1){
                        binding.r1b1.text = "X"
                    } else {
                        binding.r1b1.text = "O"
                    }

                }
                else {
                    Toast.makeText(this, "That spot is taken kid", Toast.LENGTH_SHORT).show()
                }
            }

            r1b2.setOnClickListener {

                if (binding.r1b2.text == "") {

                    val token = setToken(1, 2)

                    if (token == 1){
                        binding.r1b2.text = "X"
                    } else {
                        binding.r1b2.text = "O"
                    }

                }
                else {
                    Toast.makeText(this, "That spot is taken kid", Toast.LENGTH_SHORT).show()
                }
            }

            r2b0.setOnClickListener {

                if (binding.r2b0.text == "") {

                    val token = setToken(2, 0)

                    if (token == 1){
                        binding.r2b0.text = "X"
                    } else {
                        binding.r2b0.text = "O"
                    }

                }
                else {
                    Toast.makeText(this, "That spot is taken kid", Toast.LENGTH_SHORT).show()
                }

            }

            r2b1.setOnClickListener {

                if (binding.r2b1.text == "") {

                    val token = setToken(2, 1)

                    if (token == 1){
                        binding.r2b1.text = "X"
                    } else {
                        binding.r2b1.text = "O"
                    }

                }
                else {
                    Toast.makeText(this, "That spot is taken kid", Toast.LENGTH_SHORT).show()
                }
            }

            r2b2.setOnClickListener {

                if (binding.r2b2.text == "") {

                    val token = setToken(2, 2)

                    if (token == 1){
                        binding.r2b2.text = "X"
                    } else {
                        binding.r2b2.text = "O"
                    }

                }
                else {
                    Toast.makeText(this, "That spot is taken kid", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun updateGridButton(state: Int, gridbutton: Button){

        when (state) {

            0 -> gridbutton.text = ""
            1 -> gridbutton.text = "X"
            2 -> gridbutton.text = "O"

            else -> {
                println("SOMETHING IS NOT QUITE RIGHT FAM")
            }
        }
    }

    private fun setupGrid(){

        val grid: MutableList<Button> = mutableListOf(
                r0b0, r0b1, r0b2,
                r1b0, r1b1, r1b2,
                r2b0, r2b1, r2b2
        )

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

    fun setToken(row: Int, spot: Int): Int{

        val playerToken: Int

        if (GameHolder.PickedGame!!.players[0] == GameManager.player){

            playerToken = 1

        } else {

            playerToken = 2

        }

        GameManager.putMove(playerToken, row, spot)

        return playerToken
    }

    @SuppressLint("SetTextI18n")
    private fun displayWinner(player: Int){

        binding.winnerText.text = "${GameHolder.PickedGame!!.players[player - 1]} Wins!"
        binding.winnerText.setTextColor(Color.parseColor("#0aad3f"))

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