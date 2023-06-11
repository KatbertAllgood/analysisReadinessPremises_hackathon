package com.example.data.mappers

import com.example.data.models.ObjectParamsData
import com.example.domain.models.ObjectParamsDomain

class ObjectParamsToDomain(
    private val objectParamsData: ObjectParamsData
) {
    fun toDomain() = ObjectParamsDomain(
        objectParamsData.id_object,
        objectParamsData.name,
        objectParamsData.count_section,
        objectParamsData.count_flats,
        objectParamsData.userId,
        objectParamsData.date,
        objectParamsData.street,
        objectParamsData.building,
        objectParamsData.general_readiness,
        objectParamsData.ready_section,
    )
}