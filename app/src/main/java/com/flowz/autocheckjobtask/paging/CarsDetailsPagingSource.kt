package com.flowz.agromailjobtask.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.flowz.autocheckjobtask.models.carbrandsmodels.Make
import com.flowz.autocheckjobtask.models.cardetails.CarMedia
import com.flowz.autocheckjobtask.models.carlistmodels.Result
import com.flowz.autocheckjobtask.network.ApiServiceCalls

class CarsDetailsPagingSource(private val apiService: ApiServiceCalls, private val carId:String): PagingSource<Int, CarMedia>() {

    override fun getRefreshKey(state: PagingState<Int, CarMedia>): Int? {
      return null
    }


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CarMedia> {
        return try {

            val itemLimit = 10
            val numberOfPages = 12
            val currentPage = params.key ?:1

            val response = apiService.getCarDetail(carId)
//            Log.e(TAG, "$response")
            val data = response.body()?.carMediaList?: emptyList()

            val responseData = mutableListOf<CarMedia>()
            responseData.addAll(data)

            Log.e(TAG, "$responseData")

            LoadResult.Page(
                data = responseData,
                prevKey = if (currentPage==1) null else -1,
                nextKey = currentPage.plus(1)
            )

        }catch (e:Exception){
            LoadResult.Error(e)
        }
    }


    companion object{
        const val TAG = "PAGING SOURCE"
    }

}

