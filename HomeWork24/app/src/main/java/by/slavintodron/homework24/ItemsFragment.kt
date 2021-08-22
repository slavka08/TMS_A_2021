package by.slavintodron.homework24

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import by.slavintodron.homework24.databinding.FragmentItemsListBinding

class ItemsFragment : Fragment() {
    private val binding: FragmentItemsListBinding get() = _binding!!
    private var _binding: FragmentItemsListBinding? = null
    private val viewModel: ItemsViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentItemsListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvItemList.layoutManager = GridLayoutManager(context, 2)
        postponeEnterTransition()
        viewModel.itemsList.observe(viewLifecycleOwner)
        {
            val adapter = RecyclerAdapter(it.toList())
            binding.rvItemList.adapter = adapter
            (view.parent as? ViewGroup)?.doOnPreDraw {
                startPostponedEnterTransition()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}