package com.example.data.mappers

import com.example.data.models.LoginResponseData
import com.example.domain.models.LoginResponseDomain

class LoginResponseToData(
    private val loginResponseDomain: LoginResponseDomain
) {
    fun toData() = LoginResponseData(
        loginResponseDomain.token
    )
}