package by.slavintodron.homework21.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import by.slavintodron.homework21.R
import by.slavintodron.homework21.databinding.ActivityMainChooseTaskBinding
import by.slavintodron.homework21.databinding.FragmentMailListBinding

class MainActivityChooseTask : AppCompatActivity() {
    lateinit var binding: ActivityMainChooseTaskBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainChooseTaskBinding.inflate(layoutInflater)
        binding.buttonOpenPlayer.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        binding.buttonOpenMail.setOnClickListener {
            val intent = Intent(this, MailActivity::class.java)
            startActivity(intent)
        }
        setContentView(binding.root)
    }
}