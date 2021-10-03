package by.tms.homework25room.fragment

import by.tms.homework25room.CatApplication
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.tms.homework25room.R
import by.tms.homework25room.databinding.FragmentAddBinding
import by.tms.homework25room.activity.MainActivityInterface
import by.tms.homework25room.entity.Cat
import by.tms.homework25room.viewmodel.MainViewModel


private const val ARG_ID = "id"

class AddFragment : Fragment() {
    private var paramId: Int? = null

    private var _binding: FragmentAddBinding? = null
    private var navigation: MainActivityInterface? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by viewModels {
        MainViewModel.MainViewModelFactory(
            (activity?.application as CatApplication).repository,
            (activity?.application as CatApplication).prefs
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            paramId = it.getInt(ARG_ID)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is MainActivityInterface)
            navigation = context
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddBinding.inflate(inflater, container, false)
        paramId?.let { viewModel.getCatById(it) }
        when (paramId == null) {
            true -> {
                binding.buttonAdd.setText(R.string.add)
                clearForm()
            }
            else -> {
                binding.buttonAdd.setText(R.string.update)
                initObservers()
            }
        }

        binding.buttonAdd.setOnClickListener {
            when (paramId == null) {
                true -> insertIndb()
                else -> updateIndb()
            }

        }
        return binding.root
    }

    private fun clearForm() {
        binding.inputCatName.setText("")
        binding.inputCatAge.setText("")
    }

    private fun updateIndb() {
        viewModel.update(
            Cat(
                ID = paramId ?: 0,
                NAME = binding.inputCatName.text.toString(),
                AGE = getAge()
            )
        )
        navigation?.openMain()
    }

    private fun insertIndb() {
        viewModel.insert(
            Cat(
                NAME = binding.inputCatName.text.toString(),
                AGE = getAge()
            )
        )
        navigation?.openMain()
    }

    private fun getAge(): Int {
        return binding.inputCatAge.text.toString().toIntOrNull() ?: 0
    }

    private fun initObservers() {
        viewModel.cat.observe(viewLifecycleOwner) {
            binding.inputCatName.setText(it.NAME)
            binding.inputCatAge.setText(it.AGE.toString())
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance(id: Int) =
            AddFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_ID, id)
                }
            }

        @JvmStatic
        fun newInstance() = AddFragment()

    }
}