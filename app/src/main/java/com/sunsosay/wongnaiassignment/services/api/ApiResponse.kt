package com.sunsosay.wongnaiassignment.services.api

import com.google.gson.annotations.SerializedName

data class ApiResponse<T>(
    @SerializedName("status")
    var status: String? = null,
    @SerializedName("data")
    var data: T? = null
)