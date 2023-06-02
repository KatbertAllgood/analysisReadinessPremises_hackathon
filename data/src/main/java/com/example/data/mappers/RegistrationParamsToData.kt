package com.example.data.mappers

import com.example.data.models.RegistrationParamsData
import com.example.domain.models.RegistrationParamsDomain

class RegistrationParamsToData(
    private val registrationParamsDomain: RegistrationParamsDomain
) {
    fun toData() = RegistrationParamsData(
        registrationParamsDomain.fio,
        registrationParamsDomain.login,
        registrationParamsDomain.password,
        registrationParamsDomain.email,
        registrationParamsDomain.photoUrl,
        registrationParamsDomain.countObject,
    )
}