package by.slavintodron.homework24

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import by.slavintodron.homework24.entity2.Movie
import com.squareup.picasso.Picasso
import java.lang.Exception

private const val ARG_PARAM1 = "param1"

class ItemFragment : Fragment() {
    private var param1: Int? = null
    private val viewModel: ItemsViewModel by activityViewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getInt(ARG_PARAM1)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_item, container, false)
        val tvTitle = view.findViewById<TextView>(R.id.tvTitle)
        val tvInfo = view.findViewById<TextView>(R.id.tvInfo)
        val ivImg = view.findViewById<ImageView>(R.id.ivImageItemFrag)

        var item: Movie? = null
        param1?.let { item = viewModel.getItem(it) }
        Picasso.get()
            .load(item?.imageurl)
            .into(ivImg)
        tvTitle.text = item?.name
        tvInfo.text = item?.bio
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val tvTitle = view.findViewById<TextView>(R.id.tvTitle)
        param1?.let {
            val item = viewModel.getItem(it)
            tvTitle.text = item?.name
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: Int) =
            ItemFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_PARAM1, param1)
                }
            }
    }
}