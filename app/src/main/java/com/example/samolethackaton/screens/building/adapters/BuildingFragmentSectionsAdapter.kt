package com.example.samolethackaton.screens.building.adapters

import android.animation.ObjectAnimator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.models.SectionParamsDomain
import com.example.samolethackaton.R
import com.example.samolethackaton.databinding.ItemSectionBinding

class BuildingFragmentSectionsAdapter(
    private val sectionsList : List<SectionParamsDomain>
) : RecyclerView.Adapter<BuildingFragmentSectionsAdapter.SectionHolder>() {

    var onItemClick : ((SectionParamsDomain) -> Unit)? = null
    private var selectedItemPosition = -1

    private val TAG = BuildingFragmentSectionsAdapter::class.simpleName


    inner class SectionHolder(item : View) : RecyclerView.ViewHolder(item) {
        private val binding = ItemSectionBinding.bind(item)

        val cardView = binding.root
        val cl = binding.itemSectionClRoot

        fun bind(section: SectionParamsDomain, position: Int) = with(binding) {

            val positionPlusOne = position + 1

            itemSectionTvSectionNumber.text = positionPlusOne.toString()

            itemSectionPbSectionProgress.progress = section.section_progress

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SectionHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(
                R.layout.item_section,
                parent,
                false
            )
        return SectionHolder(view)
    }

    override fun getItemCount(): Int {
        return sectionsList.size
    }

    override fun onBindViewHolder(holder: SectionHolder, position: Int) {
        holder.bind(sectionsList[position], position)

        val section = sectionsList[position]
        holder.itemView.setOnClickListener {
            onItemClick?.invoke(section)
            selectedItemPosition = holder.adapterPosition
            notifyDataSetChanged()
        }

        if(selectedItemPosition == position) {

            val animator = ObjectAnimator.ofFloat(holder.cardView, "cardElevation", 0F, 10F)
            animator.start()
            holder.cl.setBackgroundResource(R.color.white)

//            holder.cardView.elevation = 10F
        } else {
            holder.cl.setBackgroundResource(R.color.fragment_background)
            holder.cardView.elevation = 0F
        }
    }

}