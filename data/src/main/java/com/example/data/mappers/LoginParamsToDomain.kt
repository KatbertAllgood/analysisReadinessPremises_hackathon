package com.example.data.mappers

import com.example.data.models.LoginParamsData
import com.example.domain.models.LoginParamsDomain

class LoginParamsToDomain(
    private val loginParamsData: LoginParamsData
) {
    fun toDomain() = LoginParamsDomain(
        loginParamsData.login,
        loginParamsData.password,
    )
}