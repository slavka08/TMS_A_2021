package by.slavintodron.homework20

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import by.slavintodron.homework20.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    // private val viewModel: MainViewModel by viewModels()

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        var viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        viewModel.timerLiveData.observe(
            this,
            {
                binding.tvTimer.text = it.toString()
            }
        )
        binding.button.setOnClickListener {
            viewModel.startTimer(binding.tvInputTimerSec.text.toString().toLongOrNull())
        }
        CreateFragment()
        setContentView(binding.root)
    }

    private fun CreateFragment() {
        val fragment = FragmentTimer.newInstance()
        supportFragmentManager.beginTransaction()
            .replace(R.id.lFrame, fragment)
            .commit()
    }
}