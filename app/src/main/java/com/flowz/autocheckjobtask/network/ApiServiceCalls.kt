package com.flowz.autocheckjobtask.network

import com.flowz.autocheckjobtask.models.AllBrands
import com.flowz.autocheckjobtask.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServiceCalls {

    @GET(Constants.END_POINT)
    suspend fun getPopularMakes(@Query("popular") yesOrNo:Boolean): AllBrands
}