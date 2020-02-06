package com.sunsosay.wongnaiassignment.model

import com.google.gson.annotations.SerializedName

data class Coin(
    @SerializedName("id")
    var id: Int? = null,
    @SerializedName("uuid")
    var uuid: String? = null,
    @SerializedName("slug")
    var slug: String? = null,
    @SerializedName("symbol")
    var symbol: String? = null,
    @SerializedName("name")
    var name: String? = null,
    @SerializedName("description")
    var description: String? = null,
    @SerializedName("color")
    var color: String? = null,
    @SerializedName("iconType")
    var iconType: String? = null,
    @SerializedName("iconUrl")
    var iconUrl: String? = null,
    @SerializedName("websiteUrl")
    var websiteUrl: String? = null,
    @SerializedName("socials")
    var socials: List<Social>? = null

)