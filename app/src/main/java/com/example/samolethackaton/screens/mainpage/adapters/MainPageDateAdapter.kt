package com.example.samolethackaton.screens.mainpage.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.samolethackaton.R
import com.example.samolethackaton.databinding.ItemDateBinding

class MainPageDateAdapter(
    private val datesList : List<Pair<String, String>>,
    private val context : Context
) : RecyclerView.Adapter<MainPageDateAdapter.DateHolder>() {

    private val TAG = MainPageDateAdapter::class.simpleName

    var onItemClick : ((Pair<String, String>) -> Unit)? = null
    private var selectedItemPosition = -1

    inner class DateHolder(item: View) : RecyclerView.ViewHolder(item) {
        private val binding = ItemDateBinding.bind(item)

        val root = binding.itemDateClRoot
        val dateTextView = binding.itemDateTvDate
        val notificationTextView = binding.itemDateTvNotification

        fun bind (date: Pair<String, String>, position: Int, isSelected: Boolean) = with(binding) {


            itemDateTvNotification.text = date.second

            val splittedDate = date.first.split('.')

            itemDateTvDate.text = "${splittedDate[0]}.${splittedDate[1]}"


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DateHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(
                R.layout.item_date,
                parent,
                false
            )
        return DateHolder(view)
    }

    override fun getItemCount(): Int {
        return datesList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: DateHolder, position: Int) {
        holder.bind(datesList[position], holder.adapterPosition, true)

        val dateOnClick = datesList[position]
        holder.itemView.setOnClickListener {
            onItemClick?.invoke(dateOnClick)
            selectedItemPosition = holder.adapterPosition
            notifyDataSetChanged()
        }

        if (selectedItemPosition == position) {
            holder.apply {
                root.setBackgroundResource(R.color.samolet_primary)
                dateTextView.setTextColor(ContextCompat.getColor(context, R.color.white))
                notificationTextView.setTextColor(ContextCompat.getColor(context, R.color.black))
                notificationTextView.setBackgroundResource(R.drawable.shape_oval_white)
            }
        } else {
            holder.apply {

                root.setBackgroundResource(R.color.white)
                dateTextView.setTextColor(ContextCompat.getColor(context, R.color.black))
                notificationTextView.setTextColor(ContextCompat.getColor(context, R.color.white))
                notificationTextView.setBackgroundResource(R.drawable.shape_oval_blue)
            }
        }
    }
}