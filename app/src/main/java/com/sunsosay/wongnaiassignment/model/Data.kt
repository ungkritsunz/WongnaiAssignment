package com.sunsosay.wongnaiassignment.model

import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("coins")
    var coins: MutableList<Coin>? = null
)