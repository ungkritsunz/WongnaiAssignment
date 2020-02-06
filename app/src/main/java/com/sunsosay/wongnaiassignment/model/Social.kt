package com.sunsosay.wongnaiassignment.model

import com.google.gson.annotations.SerializedName

data class Social(
    @SerializedName("name")
    var name: String? = null,
    @SerializedName("url")
    var url: String? = null,
    @SerializedName("type")
    var type: String? = null
)