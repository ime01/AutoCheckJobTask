package com.flowz.introtooralanguage.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.flowz.autocheckjobtask.R
import com.flowz.autocheckjobtask.databinding.AllCarsMakeItemBinding
import com.flowz.autocheckjobtask.models.carbrandsmodels.Make


class ExplorePagingAdapter(val listener: ExplorePagingAdapter.ExploreRowClickListener) :PagingDataAdapter<Make, ExplorePagingAdapter.ExploreViewHolder>(ExploreDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExploreViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.all_cars_make_item, parent, false)
        return ExploreViewHolder(AllCarsMakeItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    }

    override fun onBindViewHolder(holder: ExploreViewHolder, position: Int) {

        val currentItem = getItem(position)

        holder.binding.apply {

            holder.itemView.apply {
                carMakerName.text = "${currentItem?.name}"

                val imageIcon = currentItem?.imageUrl

//                    coil
                    carMakerLogo.load(imageIcon){
                        error(R.drawable.ic_baseline_error_outline_24)
                        placeholder(R.drawable.ic_baseline_directions_car_24)
                        crossfade(true)
                        crossfade(1000)
                    }

                val homeUrlSplit: List<String> = imageIcon?.split("\\.")!!

                val homeImageType = homeUrlSplit[homeUrlSplit.size - 1]

//                if (homeImageType.equals("svg")) {
//
////                  val  requestBuilder = Glide.with(mActivity)
////                            .using(Glide.buildStreamModelLoader(Uri::class.java, mActivity), InputStream::class.java)
////                            .from(Uri::class.java)
////                            .`as`(SVG::class.java)
////                            .transcode(SvgDrawableTranscoder(), PictureDrawable::class.java)
////                            .sourceEncoder(sun.nio.cs.StreamEncoder())
////                            .cacheDecoder(FileToStreamDecoder<SVG>(SvgDecoder()))
////                            .decoder(SvgDecoder())
////                            .placeholder(R.drawable.ic_facebook)
////                            .error(R.drawable.ic_web)
////                            .animate(android.R.anim.fade_in)
////                            .listener(SvgSoftwareLayerSetter<Uri>())
////                    val svgImageLoader = ImageLoader(context){
////                        componentRegistry {
////                            add(SvgDecoder(context))
////                        }
////                    }
////
////                    id_image_svg.load(R.drawable.ic_directions_bus_black_24dp, svgImageLoader)
////
//
//
////                    SvgLoader.pluck()
////                            .with(mActivity) // ur activity
////                            .setPlaceHolder(R.mipmap.ic_launcher, R.mipmap.ic_launcher)//use ur place holder
////                            .load(mData.get(i).getHomeTeamBadge(), myViewHolder.homeTeamBadge);
//                } else {
////                    Glide
////                        .with(mContext)
////                        .load(mData.get(i).getHomeTeamBadge())
////                        .into(myViewHolder.homeTeamBadge);
//            }


//                Glide.with(carMakerLogo)
//                    .load(imageIcon)
//                    .circleCrop()
//                    .placeholder(R.drawable.ic_baseline_directions_car_24)
//                    .error(R.drawable.ic_baseline_error_outline_24)
//                    .fallback(R.drawable.ic_baseline_directions_car_24)
//                    .into(carMakerLogo)
            }
        }
    }
 inner class ExploreViewHolder(val binding: AllCarsMakeItemBinding) : RecyclerView.ViewHolder(binding.root) {

     init {
         binding.root.setOnClickListener {
             val item = getItem(bindingAdapterPosition)
             listener.onItemClickListener(item!!)
         }
     }


    }

    interface ExploreRowClickListener {
        fun onItemClickListener(make: Make)

    }

}