package com.flowz.autocheckjobtask.ui.explore

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.flowz.agromailjobtask.paging.ExplorePagingSource
import com.flowz.autocheckjobtask.models.AllBrands
import com.flowz.autocheckjobtask.network.ApiServiceCalls
import com.flowz.autocheckjobtask.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

enum class  AllCarsMakesApiStatus {LOADING, ERROR, DONE}

@HiltViewModel
class ExploreViewModel @Inject constructor(private val apiServiceCalls: ApiServiceCalls ,  private val repository: Repository): ViewModel() {

//    val allCarsMakeNetworkstatus = MutableLiveData<AllCarsMakesApiStatus>()
//    val allCarsMakeFromNetwork = MutableLiveData<AllBrands>()
//
    val carMakersFromNetwork = Pager(PagingConfig(pageSize = 1)){
        ExplorePagingSource(apiServiceCalls)
    }.flow.cachedIn(viewModelScope)


//    fun getAllCarsMake(){
//        viewModelScope.launch(Dispatchers.IO) {
//
//            try {
//                withContext(Dispatchers.Main){
//                    allCarsMakeNetworkstatus.value = AllCarsMakesApiStatus.LOADING
//
//                    withContext(Dispatchers.Main){
//                        allCarsMakeNetworkstatus.value = AllCarsMakesApiStatus.DONE
//                    }
//                }
//
//                allCarsMakeFromNetwork.postValue(repository.getAllCarsMake())
//
//            }catch (e:Exception){
//                e.printStackTrace()
//                withContext(Dispatchers.Main){
//                    allCarsMakeNetworkstatus.value =  AllCarsMakesApiStatus.ERROR
//                }
//            }
//
//        }
//    }



}