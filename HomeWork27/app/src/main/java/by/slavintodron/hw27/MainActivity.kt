package by.slavintodron.hw27

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import by.slavintodron.hw27.adapter.WeatherAdapter
import by.slavintodron.hw27.databinding.ActivityMainBinding
import by.slavintodron.hw27.viewmodel.ActivityViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var adapter: WeatherAdapter? = null
    private val viewModel: ActivityViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        initialData()
        initRv()
        initObservers()
        initClicListeners()
        setContentView(binding.root)
    }

    private fun initObservers() {
        viewModel.weather.observe(this, {
            adapter?.submitList(it.list)
            binding.textViewError.visibility = View.GONE
        })
        viewModel.error.observe(this, {
            binding.textViewError.text = getString(R.string.error, it)
            binding.textViewError.visibility = View.VISIBLE
        })
    }

    private fun initRv() {
        adapter = WeatherAdapter()
        binding.rvWeather.layoutManager = LinearLayoutManager(this)
        binding.rvWeather.adapter = adapter
    }

    private fun initClicListeners() {
        binding.spinnerCity.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                viewModel.getWeather(binding.spinnerCity.selectedItem.toString())
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
    }

    private fun initialData() {
        viewModel.getWeather(binding.spinnerCity.selectedItem.toString())
    }

}