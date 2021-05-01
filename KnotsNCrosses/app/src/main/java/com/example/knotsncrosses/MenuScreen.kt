package com.example.knotsncrosses

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.knotsncrosses.Firebase.FirebaseManager
import com.example.knotsncrosses.api.data.Game
import com.example.knotsncrosses.databinding.ActivityMenuScreenBinding
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.knotsncrosses.Adapter.GameRecyclerAdapter
import kotlinx.android.synthetic.main.activity_menu_screen.*

class GameHolder {

    companion object {

        var PickedGame: Game? = null

    }

}

class MenuScreen: AppCompatActivity() {

    private lateinit var binding: ActivityMenuScreenBinding
    lateinit var timer: CountDownTimer

    var pollTimeInMs = 5000L
    val timeTicks = 1000L
    var repetitions: Int = 0


    val TAG:String = "MenuScreen"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMenuScreenBinding.inflate(layoutInflater)

        binding.menuUserName.text = GameManager.player

        setContentView(binding.root)

        binding.currentGamesListing.layoutManager = LinearLayoutManager(this)
        binding.currentGamesListing.adapter = GameRecyclerAdapter(emptyList<Game>(), this::onGameClicked)


        newGameButton.setOnClickListener {

            GameManager.createGame(GameManager.player!!)

        }

        joinGameButton.setOnClickListener {

            joinGameScreen()

        }

        GameManager.onCurrentGames = {

            (binding.currentGamesListing.adapter as GameRecyclerAdapter).updateGameCollection(it)

        }

        GameManager.onRecentGame = {

            updateRecentId()

        }

        GameManager.onChanges = {

            // Future savepoint for Firebase

        }

        pollTimer()

    }

    @SuppressLint("SetTextI18n")
    fun updateRecentId(){

        binding.recentId.text = "Recent game id: " + GameManager.recentGame.gameId

    }

    private fun joinGameScreen(){

        val intent = Intent(this, JoinGame::class.java)
        startActivity(intent)

    }



    private fun onGameClicked(game: Game): Unit {

        GameHolder.PickedGame = game

        val intent = Intent(this, KNCGameActivity::class.java)

        startActivity(intent)

    }

    private fun saveGames(){

        FirebaseManager.instance.saveUserData()

    }

    fun pollTimer(){

        timer = object : CountDownTimer(pollTimeInMs, timeTicks){
            override fun onTick(millisUntilFinished: Long) {

            }

            override fun onFinish() {

                println("Det pollineres :)")
                GameManager.currentGames.forEach {

                    GameManager.pollGame(it.gameId)
                    GameManager.updateCurrentGames()

                }

                pollTimer()

            }

        }

        timer.start()

    }

}