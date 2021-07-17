package by.slavintodron.letterscalculator

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import by.slavintodron.letterscalculator.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.buttonCalculate.setOnClickListener {
            val firstWordInInt = binding.firstWord.text.toString().convertToSumOfChars()
            val secondWordInInt = binding.secondWord.text.toString().convertToSumOfChars()
            binding.textViewResult.text =
                when (binding.radioGroupOperator.checkedRadioButtonId) {
                    binding.radioButton.id -> (firstWordInInt + secondWordInInt).toString()
                    binding.radioButton2.id -> (firstWordInInt - secondWordInInt).toString()
                    binding.radioButton3.id -> (firstWordInInt * secondWordInInt).toString()
                    binding.radioButton4.id -> ((0.0f + firstWordInInt) / secondWordInInt).toString()
                    else -> ""
                }
        }
    }

    fun String.convertToSumOfChars(): Int {
        val tmp = this.uppercase(Locale.getDefault())
        val toList = tmp.toList()
        var sum = 0
        for (char in toList) {
            sum += char.code - ('А'.code) + 1
        }
        return sum
    }
}