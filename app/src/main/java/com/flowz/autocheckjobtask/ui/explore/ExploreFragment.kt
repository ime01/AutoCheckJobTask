package com.flowz.autocheckjobtask.ui.explore

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.flowz.autocheckjobtask.R
import com.flowz.autocheckjobtask.databinding.FragmentExploreBinding
import com.flowz.autocheckjobtask.models.Make
import com.flowz.byteworksjobtask.util.showSnackbar
import com.flowz.introtooralanguage.adapters.ExplorePagingAdapter
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
class ExploreFragment : Fragment(R.layout.fragment_explore),
    ExplorePagingAdapter.ExploreRowClickListener {

    private var _binding: FragmentExploreBinding? = null
    private val binding get() = _binding!!
    private lateinit var exploreAdapter: ExplorePagingAdapter
    private val viewModel: ExploreViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentExploreBinding.bind(view)

        loadRecyclerView()
        loadCarsMakers()


        exploreAdapter.addLoadStateListener { loadState->

            binding.apply {
                progressBar.isVisible = loadState.source.refresh is LoadState.Loading
                rvCarsmakersIcon.isVisible = loadState.source.refresh is LoadState.NotLoading
                buttonRetry.isVisible = loadState.source.refresh is LoadState.Error
                errorText.isVisible = loadState.source.refresh is LoadState.Error

                if (loadState.source.refresh is LoadState.NotLoading && loadState.append.endOfPaginationReached&& exploreAdapter.itemCount<1){
                    rvCarsmakersIcon.isVisible = false
                    errorText.isVisible = true
                }else{
                    errorText.isVisible = false
                }
            }

        }

    }

    @InternalCoroutinesApi
    private fun loadCarsMakers() {
        lifecycleScope.launch {
            viewModel.carMakersFromNetwork.collect {
                Log.e("Farmers","$it")
                exploreAdapter.submitData(it)
            }
        }
    }

    private fun loadRecyclerView() {
        exploreAdapter = ExplorePagingAdapter(this@ExploreFragment)

        binding.apply {

            rvCarsmakersIcon.apply {
                layoutManager = LinearLayoutManager(this.context, RecyclerView.HORIZONTAL, false )
                adapter = exploreAdapter.withLoadStateHeaderAndFooter(
                        header = CarMakersLoadStateAdapter{exploreAdapter.retry()},
                        footer = CarMakersLoadStateAdapter{exploreAdapter.retry()}
                )
                setHasFixedSize(true)
            }
        }

    }

//    private fun loadRecyclerView() {
//
//        exploreAdapter = ExploreAdapter(this@ExploreFragment)
//
//        viewModel.getAllCarsMake()
//        viewModel.allCarsMakeNetworkstatus.observe(viewLifecycleOwner, Observer {state->
//
//            state?.also {
//                when(it){
//                    AllCarsMakesApiStatus.ERROR->{
//                        showSnackbar(binding.rvCarsmakersIcon, getString(R.string.network_failure))
//                    }
//                    AllCarsMakesApiStatus.LOADING->{
//                       binding.progressBar.isVisible = true
//                    }
//                    AllCarsMakesApiStatus.DONE->{
//                        binding.progressBar.isVisible = true
//                        viewModel.allCarsMakeFromNetwork.observe(viewLifecycleOwner, Observer {
//                            it.makeList.forEach {
//
//                                it.apply {
//                                    val make =  Make(this.id, this.imageUrl, this.name)
//                                    val makeList: ArrayList<Make> = ArrayList()
//                                    exploreAdapter.submitList(makeList)
//                                    Log.e("PAYLOAD", "${makeList[1]}")
//                                    showSnackbar(binding.root, "Data Fetched")
//                                }
//                            }
//
//                        })
//                    }
//                }
//            }
//        })
//
//        binding.rvCarsmakersIcon.apply {
//            layoutManager = LinearLayoutManager(this.context, RecyclerView.HORIZONTAL, false )
//            adapter = exploreAdapter
//        }
//
//
//    }

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
       showSnackbar(binding.rvCarsmakersIcon, "{${make?.name} Selected}")
    }
}