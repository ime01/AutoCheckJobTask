package com.flowz.autocheckjobtask.ui.product

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import coil.load
import com.flowz.autocheckjobtask.R
import com.flowz.autocheckjobtask.databinding.FragmentExploreBinding
import com.flowz.autocheckjobtask.databinding.FragmentProductBinding
import com.flowz.autocheckjobtask.ui.explore.ExploreViewModel
import com.flowz.introtooralanguage.adapters.CarsListPagingAdapter
import com.flowz.introtooralanguage.adapters.ExplorePagingAdapter
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class ProductFragment : Fragment(R.layout.fragment_product) {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var carId: String? = "448831"

    private var _binding: FragmentProductBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ProductViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            carId = ProductFragmentArgs.fromBundle(it).carId
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentProductBinding.bind(view)

        loadCarDetails()


    }

    private fun loadCarDetails() {
        viewModel.query.postValue(carId)

       lifecycleScope.launch {
           viewModel.carDetailFromNetwork.collect {

               binding.apply {
//                   carRight.load(){
//                       error(R.drawable.ic_baseline_error_outline_24)
//                       placeholder(R.drawable.ic_baseline_directions_car_24)
//                       crossfade(true)
//                       crossfade(1000)
//                   }
               }


           }
       }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ProductFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ProductFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}