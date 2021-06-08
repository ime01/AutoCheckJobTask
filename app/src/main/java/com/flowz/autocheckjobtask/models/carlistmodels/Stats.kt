package com.flowz.autocheckjobtask.models.carlistmodels


import com.google.gson.annotations.SerializedName

data class Stats(
    val appViewCount: Int,
    val appViewerCount: Int,
    val interestCount: Int,
    val processedLoanCount: Int,
    val testDriveCount: Int,
    val webViewCount: Int,
    val webViewerCount: Int
)