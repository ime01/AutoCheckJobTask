package com.flowz.autocheckjobtask.ui.explore

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.flowz.agromailjobtask.paging.CarsDetailsPagingSource
import com.flowz.agromailjobtask.paging.CarsListPagingSource
import com.flowz.agromailjobtask.paging.ExplorePagingSource
import com.flowz.autocheckjobtask.network.ApiServiceCalls
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

enum class  AllCarsMakesApiStatus {LOADING, ERROR, DONE}

@HiltViewModel
class ExploreViewModel @Inject constructor(private val apiServiceCalls: ApiServiceCalls): ViewModel() {


    val carMakersFromNetwork = Pager(PagingConfig(pageSize = 1)){
        ExplorePagingSource(apiServiceCalls)
    }.flow.cachedIn(viewModelScope)

    val carsLisFromNetwork = Pager(PagingConfig(pageSize = 1)){
        CarsListPagingSource(apiServiceCalls)
    }.flow.cachedIn(viewModelScope)




}