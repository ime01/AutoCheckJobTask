package com.flowz.introtooralanguage.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.flowz.autocheckjobtask.R
import com.flowz.autocheckjobtask.databinding.CarsListItemBinding
import com.flowz.autocheckjobtask.models.carlistmodels.Result


class CarsListPagingAdapter(val listener: CarsListPagingAdapter.ExploreRowClickListener) :PagingDataAdapter<Result, CarsListPagingAdapter.CarsListViewHolder>(CarsListDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarsListViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.cars_list_item, parent, false)
        return CarsListViewHolder(CarsListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    }

    override fun onBindViewHolder(holder: CarsListViewHolder, position: Int) {

        val currentItem = getItem(position)

        holder.binding.apply {

            holder.itemView.apply {
                carMakerName.text = "${currentItem?.title}"

                val imageIcon = currentItem?.imageUrl
//                    coil
                    carMakerLogo.load(imageIcon){
                        error(R.drawable.ic_baseline_error_outline_24)
                        placeholder(R.drawable.ic_baseline_directions_car_24)
                        crossfade(true)
                        crossfade(1000)
                    }
            }
        }
    }
 inner class CarsListViewHolder(val binding: CarsListItemBinding) : RecyclerView.ViewHolder(binding.root) {

     init {
         binding.favorite.setOnClickListener {
             val item = getItem(bindingAdapterPosition)
             listener.onItemClickListener(item!!)
         }
     }


    }

    interface ExploreRowClickListener {
        fun onItemClickListener(result: Result)

    }

}