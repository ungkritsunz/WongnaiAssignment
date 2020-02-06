package com.sunsosay.wongnaiassignment.services.repository

import androidx.lifecycle.MutableLiveData
import com.sunsosay.wongnaiassignment.model.Data

interface Repository {
    fun getCoins() : MutableLiveData<Data>
}