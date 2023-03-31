package ph.stacktrek.novare.game.tuliaomiranda.snakeandladders

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import ph.stacktrek.novare.game.tuliaomiranda.snakeandladders.adapters.PlayerAdapter
import ph.stacktrek.novare.game.tuliaomiranda.snakeandladders.dao.PlayerDAO
import ph.stacktrek.novare.game.tuliaomiranda.snakeandladders.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var playerAdapter: PlayerAdapter

    private val homeFragment = HomeFragment()
    private val instructionFragment = InstructionFragment()
    private val historyFragment = HistoryFragment()

    private lateinit var playerDAO: PlayerDAO
    private lateinit var itemTouchHelper: ItemTouchHelper


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(homeFragment)



        binding.bottomNavigationView.setOnNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.home -> replaceFragment(homeFragment)
                R.id.instruction -> replaceFragment(instructionFragment)
                R.id.history -> replaceFragment(historyFragment)

                else -> {

                }
            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManger = supportFragmentManager
        val fragmentTransaction = fragmentManger.beginTransaction()
        fragmentTransaction.replace(R.id.frameLayout, fragment)
        fragmentTransaction.commit()
    }

}