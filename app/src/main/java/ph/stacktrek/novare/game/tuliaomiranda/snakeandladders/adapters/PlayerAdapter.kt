package ph.stacktrek.novare.game.tuliaomiranda.snakeandladders.adapters

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.util.rangeTo
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import ph.stacktrek.novare.game.tuliaomiranda.snakeandladders.StartGameActivity
import ph.stacktrek.novare.game.tuliaomiranda.snakeandladders.databinding.PlayerInfoBinding
import ph.stacktrek.novare.game.tuliaomiranda.snakeandladders.model.Player

class PlayerAdapter(private val context: Context,
                    private var playerList:ArrayList<Player>):
    RecyclerView.Adapter<PlayerAdapter.ViewHolder>() {


    override fun getItemCount(): Int = playerList.size
    fun deletePlayer(position: Int) {
        playerList.removeAt(position)
        notifyItemRemoved(position)
    }

    fun addPlayer(player: Player) {
            playerList.add(0, player)
            notifyItemInserted(0)

//        val startGame = StartGameActivity()
//
//        if (itemCount in 2..4) {
//            startGame.canStartGame
//        } else {
//            startGame.canStartGame = false
//        }
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val playerItemBinding = PlayerInfoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(playerItemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(playerList[position])
    }



    inner class ViewHolder(private val playerItemBinding: PlayerInfoBinding):
        RecyclerView.ViewHolder(playerItemBinding.root){

        fun bindItems(player: Player){
            playerItemBinding.playerUsername.text = player.username

        }
    }
}