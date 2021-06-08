package com.flowz.autocheckjobtask.models.cardetails


import com.google.gson.annotations.SerializedName

data class CarMedia(
    val createdAt: String,
    val id: Int,
    val name: String,
    val type: String,
    val url: String
)