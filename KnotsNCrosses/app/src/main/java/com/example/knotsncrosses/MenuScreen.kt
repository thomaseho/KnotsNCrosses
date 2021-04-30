package com.example.knotsncrosses

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.example.knotsncrosses.api.data.Game
import com.example.knotsncrosses.databinding.ActivityMenuScreenBinding

class GameHolder {

    companion object {

        var PickedGame: Game? = null

    }

}

class MenuScreen: AppCompatActivity() {

    private lateinit var binding: ActivityMenuScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMenuScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }


}