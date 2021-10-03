package by.tms.homework25room.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import by.tms.homework25room.CatApplication
import by.tms.homework25room.activity.MainActivityInterface
import by.tms.homework25room.adapter.TableRvAdapter
import by.tms.homework25room.databinding.FragmentMainBinding
import by.tms.homework25room.entity.Cat
import by.tms.homework25room.viewmodel.MainViewModel

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    var navigation: MainActivityInterface? = null
    private val viewModel: MainViewModel by viewModels {
        MainViewModel.MainViewModelFactory(
            (activity?.application as CatApplication).repository,
            (activity?.application as CatApplication).prefs
        )
    }
    private var adapter: TableRvAdapter? = null

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
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        binding.button.setOnClickListener { navigation?.openAdd() }
        binding.buttonFilter.setOnClickListener { navigation?.openFilter() }
        binding.buttonSettings.setOnClickListener { navigation?.openSetting() }
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        viewModel.reloadData()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = TableRvAdapter(
            OnClickListener { cat ->
                navigation?.openAdd(cat.ID)
            },
            OnClickListener { cat ->
                viewModel.deleteById(cat.ID)
            }
        )
        binding.rvDataBase.layoutManager = LinearLayoutManager(requireContext())
        binding.rvDataBase.adapter = adapter
        initObservers()
    }

    private fun initObservers() {
        viewModel.listCats.observe(viewLifecycleOwner) {
            adapter?.submitList(it)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    class OnClickListener(val clickListener: (cat: Cat) -> Unit) {
        fun onClick(cat: Cat) = clickListener(cat)
    }
}