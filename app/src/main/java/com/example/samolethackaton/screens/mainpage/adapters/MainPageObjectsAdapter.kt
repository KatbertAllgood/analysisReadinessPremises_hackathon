package com.example.samolethackaton.screens.mainpage.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.models.ObjectParamsDomain
import com.example.samolethackaton.R
import com.example.samolethackaton.databinding.ItemObjectBinding

class MainPageObjectsAdapter(
    private val objectsList : List<ObjectParamsDomain>,
    private val context: Context
) : RecyclerView.Adapter<MainPageObjectsAdapter.ObjectHolder>() {

    var onItemClick : ((ObjectParamsDomain, String) -> Unit)? = null
    private val TAG = MainPageObjectsAdapter::class.simpleName

    inner class ObjectHolder(item : View) : RecyclerView.ViewHolder(item) {
        private val binding = ItemObjectBinding.bind(item)

        fun bind (building : ObjectParamsDomain, position: Int) = with (binding) {

            itemObjectTvNumOfTask.text = (position + 1).toString()
            itemObjectTvAddress.text = context.getString(
                R.string.address_constructor,
                building.street,
                building.building
            )
            itemObjectTvSectionPassed.text = context.getString(
                R.string.counting_sections,
                building.ready_section.toString(),
                building.count_section.toString()
            )
            itemObjectPbCounting.max = 100
            itemObjectPbCounting.progress = building.general_readiness

            itemObjectTvNumberOfHouse.text = context.getString(
                R.string.building_num,
                building.building
            )

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ObjectHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(
                R.layout.item_object,
                parent,
                false
            )
        return ObjectHolder(view)
    }

    override fun getItemCount(): Int {
        return objectsList.size
    }

    override fun onBindViewHolder(holder: ObjectHolder, position: Int) {
        holder.bind(objectsList[position], holder.adapterPosition)

        val objectOnClick = objectsList[position]
        val numOfTask = (position + 1).toString()
        holder.itemView.setOnClickListener {
            onItemClick?.invoke(objectOnClick, numOfTask)
            notifyDataSetChanged()
        }
    }
}