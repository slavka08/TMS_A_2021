package by.slavintodron.homework20

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import by.slavintodron.homework20.databinding.FragmentTimerBinding


class FragmentTimer : Fragment() {
    private var _binding: FragmentTimerBinding? = null
    private val binding get() = _binding!!

    //private val viewModelTimer: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val viewModel = activity?.let { ViewModelProvider(it) }?.get(MainViewModel::class.java)

        _binding = FragmentTimerBinding.inflate(inflater, container, false)
        binding.button.setOnClickListener {
            viewModel?.startTimer(
                binding.tvInputTimerSec.text.toString().toLongOrNull()
            )
        }
        viewModel?.timerLiveData?.observe(
            viewLifecycleOwner,
            {
                binding.tvTimer.text = it.toString()
            }
        )
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            FragmentTimer().apply {

            }
    }
}