package ph.stacktrek.novare.game.tuliaomiranda.snakeandladders

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ph.stacktrek.novare.game.tuliaomiranda.snakeandladders.databinding.ActivityInstructionBinding
import ph.stacktrek.novare.game.tuliaomiranda.snakeandladders.databinding.ActivityMainBinding

class InstructionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityInstructionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_instruction)

        binding = ActivityInstructionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backMainButton.setOnClickListener {
            val goToMain = Intent(
                applicationContext,
                MainActivity::class.java
            )

            startActivity(goToMain)
            finish()
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