package com.flowz.autocheckjobtask.ui.product

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.flowz.agromailjobtask.paging.CarsDetailsPagingSource
import com.flowz.autocheckjobtask.network.ApiServiceCalls
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(private val apiServiceCalls: ApiServiceCalls ):ViewModel() {

    val query = MutableLiveData<String>("")

    val carDetailFromNetwork = Pager(PagingConfig(pageSize = 1)){
        CarsDetailsPagingSource(apiServiceCalls, query.value.toString() )
    }.flow.cachedIn(viewModelScope)

}