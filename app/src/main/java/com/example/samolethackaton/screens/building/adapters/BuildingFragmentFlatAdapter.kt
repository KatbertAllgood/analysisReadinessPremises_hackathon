package com.example.samolethackaton.screens.building.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.samolethackaton.R
import com.example.samolethackaton.databinding.ItemFlatBinding

class BuildingFragmentFlatAdapter(
    private val testList : List<Int>
) : RecyclerView.Adapter<BuildingFragmentFlatAdapter.FlatHolder>() {

    var onItemClick : ((Int) -> Unit)? = null

    inner class FlatHolder(item: View) : RecyclerView.ViewHolder(item) {
        val binding = ItemFlatBinding.bind(item)

        fun bind(num: Int) = with(binding) {

            itemFlatNumber.text = num.toString()

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlatHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(
                R.layout.item_flat,
                parent,
                false
            )
        return FlatHolder(view)
    }

    override fun getItemCount(): Int {
        return testList.size
    }

    override fun onBindViewHolder(holder: FlatHolder, position: Int) {
        holder.bind(testList[position])

        val number = testList[position]
        holder.itemView.setOnClickListener {
            onItemClick?.invoke(number)
            notifyDataSetChanged()
        }
    }
}