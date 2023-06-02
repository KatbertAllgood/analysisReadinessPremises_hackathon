package com.example.data.network.api

import com.example.data.network.api.interceptor.Interceptor
import com.example.data.network.client.OkHttpClient
import com.example.domain.utils.Constants
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object NetworkService {

    private val okHttpClient = OkHttpClient.getUnsafeOkHttpClient()
        .addInterceptor(Interceptor())
        .build()

    val retrofitService = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(ServerApi::class.java)

}