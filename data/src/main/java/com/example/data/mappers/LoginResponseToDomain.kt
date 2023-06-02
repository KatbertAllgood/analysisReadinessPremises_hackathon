package com.example.data.mappers

import com.example.data.models.LoginResponseData
import com.example.domain.models.LoginResponseDomain

class LoginResponseToDomain(
    private val loginResponseData: LoginResponseData
) {
    fun toDomain() = LoginResponseDomain(
        loginResponseData.token
    )
}
