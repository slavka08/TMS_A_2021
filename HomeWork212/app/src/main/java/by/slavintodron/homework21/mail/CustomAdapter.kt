package by.slavintodron.homework21.mail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import by.slavintodron.homework21.R

class CustomAdapter(
    private val dataSet: Array<MailEntity>,
    private val onClick: (Int) -> Unit
) :
    RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    class ViewHolder(view: View, onClick: (Int) -> Unit) : RecyclerView.ViewHolder(view) {
        private val tvTheme: TextView = view.findViewById(R.id.tvMailTheme)
        private val tvDate: TextView = view.findViewById(R.id.tvDate)
        private var currentMail: MailEntity? = null
        private var currentMailID: Int? = null

        init {
            itemView.setOnClickListener {
                currentMailID?.let { it1 -> onClick(it1) }
            }
        }

        fun bind(mailID: Int, mail: MailEntity) {
            currentMail = mail
            currentMailID = mailID
            tvTheme.text = mail.theme
            tvDate.text = mail.date.toString()
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.mail_item, viewGroup, false)

        return ViewHolder(view, onClick)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val mail = dataSet[position]
        viewHolder.bind(position, mail)
    }

    override fun getItemCount() = dataSet.size
}