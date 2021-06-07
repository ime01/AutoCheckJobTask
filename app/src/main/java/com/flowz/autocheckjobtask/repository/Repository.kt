package com.flowz.autocheckjobtask.repository

import com.flowz.autocheckjobtask.models.AllBrands
import com.flowz.autocheckjobtask.network.ApiServiceCalls
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor(private val allClient:ApiServiceCalls ) {

//   suspend fun  getAllCarsMake(): AllBrands{
//       return allClient.getPopularMakes(true)
//   }


}