package com.example.data.mappers

import com.example.data.models.RegistrationParamsData
import com.example.domain.models.RegistrationParamsDomain

class RegistrationParamsToDomain(
    private val registrationParamsData : RegistrationParamsData
) {
    fun toDomain() = RegistrationParamsDomain(
        registrationParamsData.fio,
        registrationParamsData.login,
        registrationParamsData.password,
        registrationParamsData.email,
        registrationParamsData.photoUrl,
        registrationParamsData.countObject,
    )
}