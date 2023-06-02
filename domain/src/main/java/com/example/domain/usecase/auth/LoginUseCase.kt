package com.example.domain.usecase.auth

import com.example.domain.models.LoginParamsDomain
import com.example.domain.repository.NetworkRepository

class LoginUseCase(
    private val networkRepository: NetworkRepository
) {
    fun invoke(
        loginParams : LoginParamsDomain
    ) = networkRepository.login(loginParams)
}