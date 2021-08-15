package by.slavintodron.homework21.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import by.slavintodron.homework21.MailViewModel
import by.slavintodron.homework21.databinding.FragmentMailListBinding
import by.slavintodron.homework21.fragments.interfaces.MailFragmentsListener
import by.slavintodron.homework21.mail.CustomAdapter
import by.slavintodron.homework21.mail.MailEntity

class MailListFragment : Fragment() {
    lateinit var listener: MailFragmentsListener
    private var _binding: FragmentMailListBinding? = null
    private val binding get() = _binding!!
    lateinit var viewModel: MailViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is MailFragmentsListener) {
            listener = context as MailFragmentsListener
        } else {
            throw RuntimeException(
                "$context must implement MailFragmentsListener"
            )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMailListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val viewModel = ViewModelProvider(requireActivity())[MailViewModel::class.java]
        viewModel.mails.observe(
            viewLifecycleOwner,
            {
                adapterMailUpdate(it)
            }
        )
    }

    private fun adapterMailUpdate(data: Array<MailEntity>) {
        val adapter = CustomAdapter(data) { mailID -> onMailClick(mailID) }
        binding.rvMail.adapter = adapter
    }

    private fun onMailClick(mailID: Int) {
        listener.openMail(mailID)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MailListFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}


