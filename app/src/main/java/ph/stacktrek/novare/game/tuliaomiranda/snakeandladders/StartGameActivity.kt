package ph.stacktrek.novare.game.tuliaomiranda.snakeandladders

import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import ph.stacktrek.novare.game.tuliaomiranda.snakeandladders.adapters.PlayerAdapter
import ph.stacktrek.novare.game.tuliaomiranda.snakeandladders.adapters.SwipeCallback
import ph.stacktrek.novare.game.tuliaomiranda.snakeandladders.dao.PlayerDAO
import ph.stacktrek.novare.game.tuliaomiranda.snakeandladders.dao.PlayerDAOSQLLiteImplementation
import ph.stacktrek.novare.game.tuliaomiranda.snakeandladders.databinding.ActivityMainBinding
import ph.stacktrek.novare.game.tuliaomiranda.snakeandladders.databinding.ActivityStartGameBinding
import ph.stacktrek.novare.game.tuliaomiranda.snakeandladders.databinding.DialogueAddPlayerBinding
import ph.stacktrek.novare.game.tuliaomiranda.snakeandladders.model.Player

class StartGameActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStartGameBinding
    private lateinit var playerAdapter: PlayerAdapter

    private lateinit var playerDAO: PlayerDAO
    private lateinit var itemTouchHelper: ItemTouchHelper

    var canStartGame: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStartGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadPlayers()

        binding.fabAddPlayerButton.setOnClickListener {
            showAddPlayerDialogue().show()
        }


//        binding.readyButton.setOnClickListener {
//            if (!canStartGame) {
//                binding.readyButton.isEnabled = false
//                Snackbar.make(binding.root,
//                    "Minimum of 2 players and Maximum of 4 players",
//                    Snackbar.LENGTH_SHORT).show()
//            }  else {
//                binding.readyButton.isEnabled = true
//                val goToGame = Intent(
//                    applicationContext,
//                    GameActivity::class.java
//                )
//                startActivity(goToGame)
//                finish()
//            }
//        }
    }

    fun loadPlayers() {
        playerDAO = PlayerDAOSQLLiteImplementation(applicationContext)
        playerAdapter = PlayerAdapter(applicationContext,
            playerDAO.getPlayers())

        with(binding.playersList) {
            layoutManager = LinearLayoutManager(applicationContext,
                LinearLayoutManager.VERTICAL,
                false)

            adapter = playerAdapter

            var swipeCallback = SwipeCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT)
            swipeCallback.playerAdapter = playerAdapter
            itemTouchHelper = ItemTouchHelper(swipeCallback).apply {
                attachToRecyclerView(binding.playersList)
            }
        }
    }

    fun showAddPlayerDialogue(): Dialog {
        return this.let {
            val builder = AlertDialog.Builder(it)
            val dialogueAddPlayerBinding: DialogueAddPlayerBinding =
                DialogueAddPlayerBinding.inflate(it.layoutInflater)

            with(builder) {
                setPositiveButton("ADD", DialogInterface.OnClickListener { dialog, id ->
                    val player = Player("")
                    player.username = dialogueAddPlayerBinding.playerUsername.text.toString()

                    val playerDAO = PlayerDAOSQLLiteImplementation(applicationContext)
                    playerDAO.addPlayer(player)
                    playerAdapter.addPlayer(player)
                })
                setNegativeButton("CANCEL", DialogInterface.OnClickListener { dialog, id ->

                })
                setView(dialogueAddPlayerBinding.root)
                create()
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val goToMain = Intent(applicationContext,
            MainActivity::class.java)
        startActivity(goToMain)
        finish()
    }
}