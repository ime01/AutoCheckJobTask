package com.flowz.autocheckjobtask.ui.explore

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.flowz.autocheckjobtask.R
import com.flowz.autocheckjobtask.databinding.FragmentExploreBinding
import com.flowz.autocheckjobtask.models.carbrandsmodels.Make
import com.flowz.autocheckjobtask.models.carlistmodels.Result
import com.flowz.byteworksjobtask.util.showSnackbar
import com.flowz.introtooralanguage.adapters.CarsListPagingAdapter
import com.flowz.introtooralanguage.adapters.ExplorePagingAdapter
import com.flowz.paging3withflow.ui.gridview.CarListLoadStateAdapter
import com.flowz.paging3withflow.ui.gridview.CarMakersLoadStateAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ExploreFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

@InternalCoroutinesApi
@AndroidEntryPoint
class ExploreFragment : Fragment(R.layout.fragment_explore), ExplorePagingAdapter.ExploreRowClickListener, CarsListPagingAdapter.ExploreRowClickListener {

    private var _binding: FragmentExploreBinding? = null
    private val binding get() = _binding!!
    private lateinit var exploreAdapter: ExplorePagingAdapter
    private lateinit var carsListPagingAdapter: CarsListPagingAdapter
    private val viewModel: ExploreViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentExploreBinding.bind(view)

        loadCarsMakersRecyclerView()
        loadCarsListRecyclerView()
        loadCarsListData()



        exploreAdapter.addLoadStateListener { loadState->

            binding.apply {
                progressBar.isVisible = loadState.source.refresh is LoadState.Loading
                rvCarsmakers.isVisible = loadState.source.refresh is LoadState.NotLoading
                buttonRetry.isVisible = loadState.source.refresh is LoadState.Error
                errorText.isVisible = loadState.source.refresh is LoadState.Error

                if (loadState.source.refresh is LoadState.NotLoading && loadState.append.endOfPaginationReached&& exploreAdapter.itemCount<1){
                    rvCarsmakers.isVisible = false
                    errorText.isVisible = true
                }else{
                    errorText.isVisible = false
                }
            }

        }

        carsListPagingAdapter.addLoadStateListener { loadState->

            binding.apply {
                carsListProgress.isVisible = loadState.source.refresh is LoadState.Loading
                rvCarsList.isVisible = loadState.source.refresh is LoadState.NotLoading
                carslistRetry.isVisible = loadState.source.refresh is LoadState.Error
                carListErrorText.isVisible = loadState.source.refresh is LoadState.Error

                if (loadState.source.refresh is LoadState.NotLoading && loadState.append.endOfPaginationReached&& carsListPagingAdapter.itemCount<1){
                    rvCarsList.isVisible = false
                    carListErrorText.isVisible = true
                }else{
                    carListErrorText.isVisible = false
                }
            }

        }

    }

    private fun loadCarsListRecyclerView() {

        carsListPagingAdapter = CarsListPagingAdapter(this@ExploreFragment)

        binding.apply {

            rvCarsList.apply {
                layoutManager = LinearLayoutManager(this.context)
                adapter = carsListPagingAdapter.withLoadStateHeaderAndFooter(
                    header = CarListLoadStateAdapter{carsListPagingAdapter.retry()},
                    footer = CarListLoadStateAdapter{carsListPagingAdapter.retry()}
                )
                setHasFixedSize(true)
            }
        }


    }


    private fun loadCarsListData() {
        lifecycleScope.launch {
            viewModel.carsLisFromNetwork.collect {
                Log.e("CarsList","$it")
                carsListPagingAdapter.submitData(it)
            }
        }
    }

    private fun loadCarsMakers() {
        lifecycleScope.launch {
            viewModel.carMakersFromNetwork.collect {
                Log.e("CarsMakes","$it")
                exploreAdapter.submitData(it)
            }
        }
    }

    private fun loadCarsMakersRecyclerView() {
        exploreAdapter = ExplorePagingAdapter(this@ExploreFragment)

        binding.apply {

            rvCarsmakers.apply {
                layoutManager = LinearLayoutManager(this.context, RecyclerView.HORIZONTAL, false )
                adapter = exploreAdapter.withLoadStateHeaderAndFooter(
                        header = CarMakersLoadStateAdapter{exploreAdapter.retry()},
                        footer = CarMakersLoadStateAdapter{exploreAdapter.retry()}
                )
                setHasFixedSize(true)
            }
        }

        loadCarsMakers()

    }
    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ExploreFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onItemClickListener(make: Make) {
       showSnackbar(binding.rvCarsmakers, "{${make?.name} Selected}")
    }

    override fun onItemClickListener(result: Result) {
        val action = ExploreFragmentDirections.actionExploreFragmentToProductFragment()
        action.carId = result.id
        Navigation.findNavController(requireView()).navigate(action)
       showSnackbar(binding.rvCarsmakers, "${result.title} is my Favorite")
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding =null
    }
}