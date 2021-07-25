package by.slavintodron.hw15

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import by.slavintodron.hw15.databinding.ActivityMainBinding
import by.slavintodron.hw15.fragment.Hw15t1
import by.slavintodron.hw15.fragment.Hw15t2
import by.slavintodron.hw15.fragment.MainFragment
import by.slavintodron.hw15.interfaces.IMainAct


class MainActivity : AppCompatActivity(), IMainAct {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        openMainFragment()
    }

    private fun openMainFragment() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frame, MainFragment.newInstance(), null)
            .commit()
    }

    private fun openTask1Fragment() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frame, Hw15t1.newInstance(), null)
            .commit()
    }

    private fun openTask4Fragment() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frame, Hw15t2.newInstance(), null)
            .commit()
    }

    override fun openMain() {
        openMainFragment()
    }

    override fun openT1() {
        openTask1Fragment()
    }

    override fun openT4() {
        openTask4Fragment()
    }
}