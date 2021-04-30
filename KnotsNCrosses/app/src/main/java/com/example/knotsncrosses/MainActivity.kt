package com.example.knotsncrosses

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.knotsncrosses.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import android.provider.Settings
import com.example.knotsncrosses.Firebase.FirebaseManager
import com.example.knotsncrosses.api.data.Game
import com.example.knotsncrosses.dialogs.CreateGameDialog
import com.example.knotsncrosses.dialogs.GameDialogListener


class MainActivity : AppCompatActivity(), GameDialogListener {

    private lateinit var binding:ActivityMainBinding
    private lateinit var auth: FirebaseAuth

    val TAG:String = "MainActivity"

    @SuppressLint("HardwareIds")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        auth = Firebase.auth
        signInAnonymously()

        val secureID = Settings.Secure.getString(contentResolver, Settings.Secure.ANDROID_ID)
        FirebaseManager.instance.setUniqueId(secureID)

        setContentView(binding.root)

        binding.startButton.setOnClickListener {

            goToMenuScreen()

        }

    }

    fun goToMenuScreen() {

        val intent = Intent(this, MenuScreen::class.java)
        startActivity(intent)

    }

    private fun signInAnonymously(){

        auth.signInAnonymously().addOnSuccessListener {
            Log.d(TAG, "Login success ${it.user}")
        }.addOnFailureListener{
            Log.e(TAG, "Login failed", it)
        }

    }

    private fun saveGames(){

        val path = this.getExternalFilesDir(null)
        FirebaseManager.instance.saveUserData(path)

    }

    override fun onDialogCreateGame(player: String) {
        Log.d(TAG, player)
    }

    override fun onDialogJoinGame(player: String, gameId: String) {
        Log.d(TAG, "$player, $gameId")
    }

}