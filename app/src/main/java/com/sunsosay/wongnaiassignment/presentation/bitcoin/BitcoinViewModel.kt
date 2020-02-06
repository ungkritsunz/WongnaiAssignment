package com.sunsosay.wongnaiassignment.presentation.bitcoin

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sunsosay.wongnaiassignment.model.Coin
import com.sunsosay.wongnaiassignment.model.Data
import com.sunsosay.wongnaiassignment.services.repository.Repository

class BitcoinViewModel(private val repository: Repository) : ViewModel() {
    var dataListCoin: MutableLiveData<MutableList<Coin>> = MutableLiveData()
    var sizeItemAdapter = 10
    fun getCoins(): MutableLiveData<Data>? {
        return repository.getCoins()
    }
}