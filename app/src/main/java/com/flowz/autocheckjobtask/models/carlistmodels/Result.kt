package com.flowz.autocheckjobtask.models.carlistmodels


import com.google.gson.annotations.SerializedName

data class Result(
    val bodyTypeId: String,
    val city: String,
    val depositReceived: Boolean,
    val gradeScore: Double,
    val hasFinancing: Boolean,
    val hasThreeDImage: Boolean,
    val hasWarranty: Boolean,
    val id: String,
    val imageUrl: String,
    val installment: Int,
    val loanValue: Int,
    val marketplaceOldPrice: Int,
    val marketplacePrice: Int,
    val mileage: Int,
    val mileageUnit: String,
    val sellingCondition: String,
    val sold: Boolean,
    val soldDate: String,
    val state: String,
    val stats: Stats,
    val title: String,
    val websiteUrl: String,
    val year: Int
)