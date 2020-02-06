package com.sunsosay.wongnaiassignment.services.repository

import androidx.lifecycle.MutableLiveData
import com.sunsosay.wongnaiassignment.model.Data
import com.sunsosay.wongnaiassignment.services.ServiceManager
import com.sunsosay.wongnaiassignment.services.api.ApiInterface
import org.koin.dsl.module

val repositoryModule = module {
    single<Repository> { RepositoryImpl(get()) }
}

class RepositoryImpl(private val remoteDataSource: ApiInterface) : Repository {
    override fun getCoins(): MutableLiveData<Data> {
        val data: MutableLiveData<Data> = MutableLiveData()
        ServiceManager.service(
            call = remoteDataSource.getCoins()
        ) { dataResponse ->
            dataResponse?.let {
                data.value = it.data
            }
        }
        return data
    }
}