package by.slavintodron.homework19

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import by.slavintodron.homework19.databinding.ActivityMainBinding

const val FLAG = "flag"

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonde.setOnClickListener {
            startFlagIntent(R.layout.activity_flag_de)
        }
        binding.buttonpl.setOnClickListener {
            startFlagIntent(R.layout.activity_flag_pl)
        }
        binding.buttonjp.setOnClickListener {
            startFlagIntent(R.layout.activity_flag_jpn)
        }
        binding.buttonit.setOnClickListener {
            startFlagIntent(R.layout.activity_flag_it)
        }
        binding.buttonirq.setOnClickListener {
            startFlagIntent(R.layout.activity_flag_irq)
        }
    }

    private fun startFlagIntent(layoutId: Int) {
        val intent = Intent(this, MainActivityFlag::class.java).apply {
            putExtra(FLAG, layoutId)
        }
        startActivity(intent)
    }

}