package com.example.domain.usecase.auth

import com.example.domain.models.RegistrationParamsDomain
import com.example.domain.repository.NetworkRepository

class RegisterUseCase(
    private val networkRepository: NetworkRepository
) {
    fun invoke(
        registerParams : RegistrationParamsDomain
    ) = networkRepository.register(registerParams)
}