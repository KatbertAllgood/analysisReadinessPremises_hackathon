package com.example.data.network.api.interceptor

import okhttp3.Interceptor
import okhttp3.Response

class Interceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val request = chain.request().newBuilder()
            .addHeader("Vary", "Origin")
            .build()
        return chain.proceed(request)
    }

}