package com.example.knotsncrosses

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
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

                if (it.players.size > 1) {

                    binding.playerTwoName.text = GameHolder.PickedGame!!.players[1]

                }

            }

            r0b0.setOnClickListener {
                setToken(0, 0)
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

    fun setToken(row: Int, spot: Int){

        val playerToken: Int

        if (GameHolder.PickedGame!!.players[0] == GameManager.player){

            playerToken = 1

        } else {

            playerToken = 2

        }

    }
}