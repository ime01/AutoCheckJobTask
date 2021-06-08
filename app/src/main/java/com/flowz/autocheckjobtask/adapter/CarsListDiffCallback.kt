package com.flowz.introtooralanguage.adapters

import androidx.recyclerview.widget.DiffUtil
import com.flowz.autocheckjobtask.models.carbrandsmodels.Make
import com.flowz.autocheckjobtask.models.carlistmodels.Result

class CarsListDiffCallback : DiffUtil.ItemCallback<Result>(){


    override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
        return oldItem == newItem
    }

}