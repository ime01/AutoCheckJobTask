package com.flowz.autocheckjobtask.models


import com.google.gson.annotations.SerializedName

data class AllBrands(
    val makeList: List<Make>,
    val pagination: Pagination
)