package com.flowz.introtooralanguage.adapters

import androidx.recyclerview.widget.DiffUtil
import com.flowz.autocheckjobtask.models.carbrandsmodels.Make

class ExploreDiffCallback : DiffUtil.ItemCallback<Make>(){


    override fun areItemsTheSame(oldItem: Make, newItem: Make): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Make, newItem: Make): Boolean {
        return oldItem == newItem
    }

}