package com.example.knotsncrosses

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.knotsncrosses.Firebase.FirebaseManager
import com.example.knotsncrosses.api.data.Game
import com.example.knotsncrosses.databinding.ActivityMenuScreenBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import android.provider.Settings
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.knotsncrosses.Adapter.GameRecyclerAdapter
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_menu_screen.*

class GameHolder {

    companion object {

        var PickedGame: Game? = null

    }

}

class MenuScreen: AppCompatActivity() {

    private lateinit var binding: ActivityMenuScreenBinding
    private lateinit var auth: FirebaseAuth

    val TAG:String = "MenuScreen"

    @SuppressLint("HardwareIds")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMenuScreenBinding.inflate(layoutInflater)

        binding.menuUserName.text = GameManager.player

        auth = Firebase.auth
        signInAnonymously()

        setContentView(binding.root)


        val secureID = Settings.Secure.getString(contentResolver, Settings.Secure.ANDROID_ID)
        FirebaseManager.instance.setUniqueId(secureID)

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

            saveGames()

        }

        GameManager.loadGames()

    }

    @SuppressLint("SetTextI18n")
    fun updateRecentId(){

        binding.recentId.text = "Recent game id: " + GameManager.recentGame.gameId

    }

    private fun joinGameScreen(){

        val intent = Intent(this, JoinGame::class.java)
        startActivity(intent)

    }

    private fun signInAnonymously(){

        auth.signInAnonymously().addOnSuccessListener {
            Log.d(TAG, "Login success ${it.user}")
        }.addOnFailureListener{
            Log.e(TAG, "Login failed", it)
        }

    }

    private fun onGameClicked(game: Game): Unit {

        GameHolder.PickedGame = game

        val intent = Intent(this, KNCGameActivity::class.java)

        startActivity(intent)

    }

    private fun saveGames(){

        val path = this.getExternalFilesDir(null)
        FirebaseManager.instance.saveUserData(path)

    }

}