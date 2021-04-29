package com.example.knotsncrosses

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.knotsncrosses.api.GameService
import com.example.knotsncrosses.api.GameServiceCallback
import com.example.knotsncrosses.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    val TAG:String = "MainActivity"

    lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}