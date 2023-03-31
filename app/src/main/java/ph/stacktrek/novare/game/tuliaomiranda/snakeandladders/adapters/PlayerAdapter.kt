package ph.stacktrek.novare.game.tuliaomiranda.snakeandladders.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import ph.stacktrek.novare.game.tuliaomiranda.snakeandladders.databinding.PlayerInfoBinding
import ph.stacktrek.novare.game.tuliaomiranda.snakeandladders.model.Player

class PlayerAdapter(private val context: Context,
                    private var playerList:ArrayList<Player>):
    RecyclerView.Adapter<PlayerAdapter.ViewHolder>() {

    fun deletePlayer(position: Int) {
        playerList.removeAt(position)
        notifyItemRemoved(position)
    }

    fun addPlayer(player: Player) {
        playerList.add(0, player)
        notifyItemInserted(0)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val playerItemBinding = PlayerInfoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(playerItemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(playerList[position])
    }

    override fun getItemCount(): Int = playerList.size

    inner class ViewHolder(private val playerItemBinding: PlayerInfoBinding):
        RecyclerView.ViewHolder(playerItemBinding.root){

        fun bindItems(player: Player){
            playerItemBinding.playerUsername.text = player.username

//            playerItemBinding.viewPlayerButton.setOnClickListener {
//                Toast.makeText(context, "${player.username}", Toast.LENGTH_SHORT).show()
//            }


        }
    }
}