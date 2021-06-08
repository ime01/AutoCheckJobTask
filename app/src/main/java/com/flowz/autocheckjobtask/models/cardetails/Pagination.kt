package com.flowz.autocheckjobtask.models.cardetails


import com.google.gson.annotations.SerializedName

data class Pagination(
    val currentPage: Int,
    val pageSize: Int,
    val total: Int
)