package com.example.samolethackaton.screens.mainpage.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.models.ObjectParamsDomain
import com.example.samolethackaton.R
import com.example.samolethackaton.databinding.ItemObjectBinding

class MainPageObjectsAdapter(
    private val objectsList : List<ObjectParamsDomain>
) : RecyclerView.Adapter<MainPageObjectsAdapter.ObjectHolder>() {

    inner class ObjectHolder(item : View) : RecyclerView.ViewHolder(item) {
        private val binding = ItemObjectBinding.bind(item)

        fun bind (building : ObjectParamsDomain, position: Int) = with (binding) {

            itemObjectTvNumOfTask.text = (position + 1).toString()
            itemObjectTvAddress.text = building.address
//            itemObjectTvSectionPassed.text
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
    }
}