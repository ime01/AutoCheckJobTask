package com.flowz.autocheckjobtask.network

import com.flowz.autocheckjobtask.models.carbrandsmodels.AllBrands
import com.flowz.autocheckjobtask.models.carlistmodels.CarsListModel
import com.flowz.autocheckjobtask.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServiceCalls {

    @GET(Constants.END_POINT)
    suspend fun getPopularMakes(@Query("popular") yesOrNo:Boolean): Response<AllBrands>

    @GET(Constants.END_POINT_CARSLIST)
    suspend fun getCarsList(): Response<CarsListModel>
}