package com.flowz.autocheckjobtask.models.cardetails


import com.google.gson.annotations.SerializedName

data class CarDetails(
    val carMediaList: List<CarMedia>,
    val pagination: Pagination
)