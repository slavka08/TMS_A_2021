package by.slavintodron.magicball

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import by.slavintodron.magicball.classes.MagicBall
import by.slavintodron.magicball.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val magicBall = MagicBall(this)
        binding.buttonGenerate.setOnClickListener {
            if (binding.qInput.text?.isNotEmpty() == true) {
                binding.textViewAnswer.text = magicBall.getRandomAnswer()
            } else {
                binding.textViewAnswer.text = resources.getText(R.string.enter_question)
            }
        }

    }
}