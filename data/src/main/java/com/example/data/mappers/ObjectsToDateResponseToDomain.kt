package com.example.data.mappers

import com.example.data.models.ObjectParamsData
import com.example.data.models.ObjectsToDateResponseData
import com.example.domain.models.ObjectParamsDomain
import com.example.domain.models.ObjectsToDateResponseDomain

class ObjectsToDateResponseToDomain(
    private val objectsToDateResponseData: ObjectsToDateResponseData
) {

    private val objects : MutableList<ObjectParamsDomain> = mutableListOf()

    init {
        for (i in objectsToDateResponseData.objects) {
            objects.add(ObjectParamsToDomain(i).toDomain())
        }
    }

    fun toDomain() = ObjectsToDateResponseDomain(
        objectsToDateResponseData.date,
        objects
    )
}