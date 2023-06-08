package com.example.data.mappers

import com.example.data.models.ObjectParamsData
import com.example.domain.models.ObjectParamsDomain

class ObjectParamsToDomain(
    private val objectParamsData: ObjectParamsData
) {
    fun toDomain() = ObjectParamsDomain(
        objectParamsData.id_object,
        objectParamsData.name,
        objectParamsData.address,
        objectParamsData.count_section,
        objectParamsData.count_flats,
    )
}