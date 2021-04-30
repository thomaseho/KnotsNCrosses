package com.example.knotsncrosses.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.knotsncrosses.api.data.Game
import com.example.knotsncrosses.databinding.MenuGameLayoutBinding

class GameRecyclerAdapter(private var games:List<Game>, private val onGameClicked:(Game) -> Unit): RecyclerView.Adapter<GameRecyclerAdapter.ViewHolder>() {

    class ViewHolder(val binding: MenuGameLayoutBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(Game: Game, onGameClicked: (Game) -> Unit) {

            binding.playerOneName.text = Game.players[0]

            if (Game.players.size > 1){

                binding.playerTwoName.text = Game.players[1]

            }

            binding.card.setOnClickListener {

                onGameClicked(Game)

            }

        }

    }

    override fun getItemCount(): Int = games.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val game = games[position]
        holder.bind(game, onGameClicked)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(MenuGameLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    }

    fun updateGameCollection(newGames: List<Game>) {

        games = newGames
        notifyDataSetChanged()

    }

}