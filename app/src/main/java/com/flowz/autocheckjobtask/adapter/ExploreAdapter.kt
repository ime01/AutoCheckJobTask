package com.flowz.introtooralanguage.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.Glide
import com.flowz.autocheckjobtask.R
import com.flowz.autocheckjobtask.databinding.AllCarsMakeItemBinding
import com.flowz.autocheckjobtask.models.Make


class ExploreAdapter(val listener: ExploreAdapter.ExploreRowClickListener) :ListAdapter<Make, ExploreAdapter.ExploreViewHolder>(ExploreDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExploreViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.all_cars_make_item, parent, false)
        return ExploreViewHolder(AllCarsMakeItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    }

    override fun onBindViewHolder(holder: ExploreViewHolder, position: Int) {

        val currentItem = getItem(position)

        holder.binding.apply {

            holder.itemView.apply {
                carMakerName.text = "{$currentItem?.name}"
                val imageIcon = currentItem?.imageUrl

                Glide.with(carMakerLogo)
                    .load(imageIcon)
                    .circleCrop()
                    .placeholder(R.drawable.ic_baseline_directions_car_24)
                    .error(R.drawable.ic_baseline_error_outline_24)
                    .fallback(R.drawable.ic_baseline_directions_car_24)
                    .into(carMakerLogo)
            }
        }


    }

 inner class ExploreViewHolder(val binding: AllCarsMakeItemBinding) : RecyclerView.ViewHolder(binding.root) {

     init {
         binding.root.setOnClickListener {
             val item = getItem(bindingAdapterPosition)
             listener.onItemClickListener(item)
         }
     }


    }

    interface ExploreRowClickListener {
        fun onItemClickListener(make: Make)

    }

}