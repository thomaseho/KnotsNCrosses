package com.example.knotsncrosses

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.knotsncrosses.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(){

    private lateinit var binding:ActivityMainBinding


    val TAG:String = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.startButton.setOnClickListener {

            ChooseNameScreen()

        }

    }

    private fun ChooseNameScreen() {

        val intent = Intent(this, ChooseName::class.java)
        startActivity(intent)

    }

}