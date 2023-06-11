package com.example.data.network.api

import com.example.data.network.api.interceptor.Interceptor
import com.example.data.network.client.OkHttpClient
import com.example.domain.utils.Constants
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object NetworkService {

    private val okHttpClient = OkHttpClient
        .getUnsafeOkHttpClient()
        .callTimeout(1000, TimeUnit.SECONDS)
        .readTimeout(1000, TimeUnit.SECONDS)
        .connectTimeout(1000, TimeUnit.SECONDS)
        .addInterceptor(Interceptor())
        .build()

    val retrofitService = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(ServerApi::class.java)


    val retrofitSeviceScor = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL_SCOR)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(ServerApi::class.java)

}