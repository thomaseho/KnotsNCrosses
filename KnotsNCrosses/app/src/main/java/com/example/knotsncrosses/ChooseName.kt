package com.example.knotsncrosses

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.knotsncrosses.databinding.ActivityChooseNameBinding
import kotlinx.android.synthetic.main.activity_choose_name.*

class ChooseName : AppCompatActivity() {

    private lateinit var binding: ActivityChooseNameBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityChooseNameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        saveNameButton.setOnClickListener {

            setName()

        }

    }

    private fun setName() {

        val name = binding.username.text.toString()

        if (name.isNotEmpty()){

            GameManager.player = name

            Toast.makeText(this, "Welcome gamer ${name}", Toast.LENGTH_SHORT).show()

            val intent = Intent(this, MenuScreen::class.java)
            startActivity(intent)
        } else {

            Toast.makeText(this, "Real gamers have names, make one :sadge:", Toast.LENGTH_SHORT).show()

        }


    }
}