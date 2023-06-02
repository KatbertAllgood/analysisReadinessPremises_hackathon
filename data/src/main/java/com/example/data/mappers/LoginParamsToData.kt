package com.example.data.mappers

import com.example.data.models.LoginParamsData
import com.example.domain.models.LoginParamsDomain

class LoginParamsToData(
    private val loginParamsDomain : LoginParamsDomain
) {
    fun toData() = LoginParamsData(
        loginParamsDomain.login,
        loginParamsDomain.password
    )
}