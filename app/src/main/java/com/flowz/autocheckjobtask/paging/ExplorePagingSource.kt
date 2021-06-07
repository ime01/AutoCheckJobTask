package com.flowz.agromailjobtask.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.flowz.autocheckjobtask.models.Make
import com.flowz.autocheckjobtask.network.ApiServiceCalls

class ExplorePagingSource(private val apiService: ApiServiceCalls): PagingSource<Int, Make>() {

    override fun getRefreshKey(state: PagingState<Int, Make>): Int? {
      return null
    }


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Make> {
        return try {

            val itemLimit = 10
            val numberOfPages = 12
            val currentPage = params.key ?:1

            val response = apiService.getPopularMakes(true)
//            Log.e(TAG, "$response")

            val data = response.body()?.makeList?: emptyList()

            val responseData = mutableListOf<Make>()
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
        const val TAG = "Paging Source"
    }

}

