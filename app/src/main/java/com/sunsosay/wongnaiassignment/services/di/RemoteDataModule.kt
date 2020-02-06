package com.sunsosay.wongnaiassignment.services.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.sunsosay.wongnaiassignment.BuildConfig
import com.sunsosay.wongnaiassignment.services.api.ApiInterface
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Modifier
import java.util.concurrent.TimeUnit

val remoteModule = module {
    single { RemoteDataModule().provideMdApi(get()) }
    single { RemoteDataModule().provideGsonBuilder() }
    single { RemoteDataModule().provideLogging() }
    single { RemoteDataModule().provideOkHttpClient(get()) }
    factory { RemoteDataModule().provideRetrofit(get(), get(), BuildConfig.URL) }
}

class RemoteDataModule {


    fun provideMdApi(retrofit: Retrofit): ApiInterface = retrofit.create(ApiInterface::class.java)


    fun provideRetrofit(gson: Gson, client: OkHttpClient, baseUrl: String): Retrofit {
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
        return retrofit.build()
    }


    fun provideGsonBuilder(): Gson = GsonBuilder()
        .excludeFieldsWithModifiers(Modifier.TRANSIENT)
        .create()


    fun provideOkHttpClient(logging: Interceptor): OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .addInterceptor(logging)
        .build()


    fun provideLogging(): Interceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        return logging
    }

}

