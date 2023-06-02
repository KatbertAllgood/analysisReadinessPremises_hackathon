package com.example.samolethackaton.app

import android.app.Application
import com.example.data.repository.NetworkRepositoryImpl
import com.example.domain.repository.NetworkRepository

class App : Application() {

    companion object {
        private lateinit var app: App
        private lateinit var networkRepository: NetworkRepository

        fun getNetworkRepository(): NetworkRepository {
            return networkRepository
        }
    }

    override fun onCreate() {
        super.onCreate()

        app = this
        val applicationContext = app.applicationContext
        networkRepository = NetworkRepositoryImpl()
    }

}