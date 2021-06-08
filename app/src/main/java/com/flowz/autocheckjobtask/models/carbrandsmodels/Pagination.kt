package com.flowz.autocheckjobtask.models.carbrandsmodels


import com.google.gson.annotations.SerializedName

data class Pagination(
    val currentPage: Int,
    val pageSize: Int,
    val total: Int
)