package com.example.data.mappers

import com.example.data.models.ObjectParamsData
import com.example.domain.models.ObjectParamsDomain

class ObjectParamsToData(
    private val objectParamsDomain: ObjectParamsDomain
) {
    fun toData() = ObjectParamsData(
        objectParamsDomain.id_object,
        objectParamsDomain.name,
        objectParamsDomain.address,
        objectParamsDomain.count_section,
        objectParamsDomain.count_flats,
    )
}