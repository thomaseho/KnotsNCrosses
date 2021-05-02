package com.example.knotsncrosses

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import com.example.knotsncrosses.Firebase.FirebaseManager
import com.example.knotsncrosses.api.data.Game
import com.example.knotsncrosses.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity(){

    private lateinit var binding:ActivityMainBinding
    private lateinit var auth: FirebaseAuth

    val TAG:String = "MainActivity"

    @SuppressLint("HardwareIds")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val secureID = Settings.Secure.getString(contentResolver, Settings.Secure.ANDROID_ID)
        val path = this.getExternalFilesDir(null)

        FirebaseManager.instance.setUniqueId(secureID, path)

        auth = Firebase.auth
        signInAnonymously()

        FirebaseManager.instance.loadFirebase()

        binding.startButton.setOnClickListener {

                ChooseNameScreen()

        }

    }

    private fun ChooseNameScreen() {

        lateinit var intent: Intent

        if (GameManager.player?.isNotEmpty() == true) {
            intent = Intent(this, MenuScreen::class.java)
        } else {
            intent = Intent(this, ChooseName::class.java)
        }
        startActivity(intent)

    }

    private fun signInAnonymously(){

        auth.signInAnonymously().addOnSuccessListener {
            Log.d(TAG, "Login success ${it.user}")
        }.addOnFailureListener{
            Log.e(TAG, "Login failed", it)
        }

    }

}