package com.flowz.autocheckjobtask.models.carlistmodels


import com.google.gson.annotations.SerializedName

data class CarsListModel(
    val pagination: Pagination,
    val result: List<Result>
)