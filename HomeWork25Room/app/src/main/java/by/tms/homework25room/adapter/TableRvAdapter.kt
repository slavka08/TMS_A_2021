package by.tms.homework25room.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import by.tms.homework25room.databinding.CatItemBinding
import by.tms.homework25room.entity.Cat
import by.tms.homework25room.fragment.MainFragment


class TableRvAdapter(
    private val editClick: MainFragment.OnClickListener,
    private val deleteClick: MainFragment.OnClickListener
) : ListAdapter<Cat, TableRvAdapter.MyViewHolder>(DIFF) {
    companion object {
        val DIFF = object : DiffUtil.ItemCallback<Cat>() {
            override fun areItemsTheSame(oldItem: Cat, newItem: Cat): Boolean =
                oldItem.ID == newItem.ID

            override fun areContentsTheSame(oldItem: Cat, newItem: Cat): Boolean =
                (oldItem.ID == newItem.ID && oldItem.NAME == newItem.NAME && oldItem.AGE == newItem.AGE)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder =
        MyViewHolder(
            CatItemBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            editClick,
            deleteClick
        )

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    class MyViewHolder(
        private val binder: CatItemBinding,
        private val editClick: MainFragment.OnClickListener,
        private val deleteClick: MainFragment.OnClickListener
    ) : RecyclerView.ViewHolder(binder.root) {
        fun onBind(data: Cat) {
            binder.textViewCatName.text = data.NAME
            binder.textViewCatAge.text = data.AGE.toString()
            binder.buttonEdit.setOnClickListener { editClick.onClick(data) }
            binder.buttonDelete.setOnClickListener { deleteClick.onClick(data) }
        }
    }

}