package by.slavintodron.homework24

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import androidx.recyclerview.widget.RecyclerView
import by.slavintodron.homework24.entity2.Movie
import com.skydoves.transformationlayout.TransformationLayout
import com.squareup.picasso.Picasso


class RecyclerAdapter(private val data: List<Movie>) : RecyclerView.Adapter<ItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(data[position])
        holder.btnMore.setOnClickListener {
            val activity = it.context as AppCompatActivity

            val fragment = ItemFragment.newInstance(position)
            activity.supportFragmentManager.commit {
                setReorderingAllowed(true)
                replace(R.id.fragmentContainerView, fragment)
                addToBackStack(null)
            }
        }
    }

    override fun getItemCount(): Int = data.size
}

class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val titleText: TextView = itemView.findViewById(R.id.tvTitle)
    val transformLay: TransformationLayout =
        itemView.findViewById(R.id.item_poster_transformationLayout)
    val ivImg: ImageView = itemView.findViewById(R.id.ivImage)
    val btnMore: Button = itemView.findViewById(R.id.btnReadMore)
    fun bind(item: Movie) {
        titleText.text = item.name
        Picasso.get().load(item.imageurl).into(ivImg);
    }

}