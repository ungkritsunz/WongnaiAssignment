package com.sunsosay.wongnaiassignment.services.api

import com.sunsosay.wongnaiassignment.model.Data
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
    @GET("v1/public/coins")
    fun getCoins(): Call<ApiResponse<Data>>
}