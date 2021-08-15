package by.slavintodron.homework21.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import by.slavintodron.homework21.MailViewModel
import by.slavintodron.homework21.databinding.FragmentMailBinding


private const val ARG_MAIL_ID = "paramMailID"

class MailFragment : Fragment() {
    private var _binding: FragmentMailBinding? = null
    private val binding get() = _binding!!
    private var param1: Int? = null
    private lateinit var viewModel:MailViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getInt(ARG_MAIL_ID, -1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[MailViewModel::class.java]

        param1?.let { fillForm(it) }
    }
    private fun fillForm(id: Int) {
        if (id > -1) {
            val mail = viewModel.getMail(id)
            binding.mailText.text = mail?.message
            binding.tvTheme.text = mail?.theme
            binding.tvSender.text = mail?.sender
            binding.tvDate.text = mail?.date.toString()
        }
    }

    companion object {

        @JvmStatic
        fun newInstance(id: Int?) =
            MailFragment().apply {
                arguments = Bundle().apply {
                    if (id != null) {
                        putInt(ARG_MAIL_ID, id)
                    }
                }
            }
    }
}