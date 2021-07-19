package by.slavintodron.quiz

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import by.slavintodron.quiz.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var quiz: Quiz
    private var checkAnswerCount = 4
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root

        setContentView(view)

        quiz = Quiz(this)
        getNextQ()

        binding.buttonCheckAnswer.setOnClickListener {

            if (quiz.checkAnswer(
                    binding.inputTextAnswer.text.toString(),
                    binding.textViewQuestion.text.toString()
                )
            ) {
                binding.textViewResult.text = getString(R.string.correctAns)
                getNextQ()
                binding.inputTextAnswer.text?.clear()
            } else {
                if (checkAnswerCount-- > 1) {
                    binding.textViewResult.text =
                        getString(R.string.notCorrectAns) + checkAnswerCount
                } else {
                    binding.textViewResult.text =
                        getString(R.string.correctAnsShow) + quiz.getAnswer(binding.textViewQuestion.text.toString())
                }
            }
        }
    }

    private fun getNextQ() {
        checkAnswerCount = 4
        binding.textViewQuestion.text = quiz.nextQuestion()

        if (binding.textViewQuestion.text.toString() == getString(R.string.gameOver)) {
            binding.textViewResult.visibility = View.GONE
            binding.buttonCheckAnswer.visibility = View.GONE
            binding.textInputLayout.visibility = View.GONE
        }
    }
}



