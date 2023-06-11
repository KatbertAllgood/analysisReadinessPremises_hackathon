package com.example.samolethackaton.screens.building.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.samolethackaton.R
import com.example.samolethackaton.databinding.ItemFloorBinding
import java.text.FieldPosition

class BuildingFragmentFloorAdapter(
    private val floorList : List<Int>,
    private val context : Context
) : RecyclerView.Adapter<BuildingFragmentFloorAdapter.FloorHolder>() {

    var onRecordClick : ((Boolean) -> Unit)? = null
    var onChooseClick : ((Boolean) -> Unit)? = null
    var onFlatClick : ((Int) -> Unit)? = null

    private var selectedItem = -1
    var isHided = true

    inner class FloorHolder(item: View) : RecyclerView.ViewHolder(item) {
        private val binding = ItemFloorBinding.bind(item)

        val ll = binding.itemFloorLlFlats
        val iv = binding.itemFloorIvArrow

        fun bind(position: Int) = with(binding) {

            val floorNum = (position + 1).toString()
            itemFloorTvNumberOfFloor.text = context.resources.getString(R.string.floor_num, floorNum)

            val flatsAdapter = BuildingFragmentFlatAdapter(floorList)

            flatsAdapter.onItemClick = {num ->

                onFlatClick?.invoke(num)
            }

            itemFloorRvFlats.apply {

                layoutManager = GridLayoutManager(context, 3)
                isNestedScrollingEnabled = false
                adapter = flatsAdapter

            }

            itemFloorIvButtonRecordVideo.setOnClickListener {

                onRecordClick?.invoke(true)
            }

            itemFloorIvButtonGalery.setOnClickListener {

                onChooseClick?.invoke(true)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FloorHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(
                R.layout.item_floor,
                parent,
                false
            )
        return FloorHolder(view)
    }

    override fun getItemCount(): Int {
        return floorList.size
    }

    override fun onBindViewHolder(holder: FloorHolder, position: Int) {
        holder.bind(position)

        holder.iv.setOnClickListener {
            selectedItem = holder.adapterPosition
            notifyDataSetChanged()
        }

        if (selectedItem == position) {

            if (isHided) {
                isHided = false
                holder.iv.setImageResource(R.drawable.ic_arrow_up)
                holder.ll.visibility = View.VISIBLE
            } else if (!isHided) {
                isHided = true
                holder.iv.setImageResource(R.drawable.ic_arrow_down)
                holder.ll.visibility = View.GONE
            }
        } else {
            isHided = true
            holder.iv.setImageResource(R.drawable.ic_arrow_down)
            holder.ll.visibility = View.GONE
        }
    }
}