package com.example.data.mappers

import com.example.data.models.ObjectParamsData
import com.example.data.models.ObjectsToDateResponseData
import com.example.domain.models.ObjectsToDateResponseDomain

class ObjectsToDateResponseToData(
    private val objectsToDateResponseDomain: ObjectsToDateResponseDomain
) {

    private val objects : MutableList<ObjectParamsData> = mutableListOf()

    init {
        for (i in objectsToDateResponseDomain.objects) {
            objects.add(ObjectParamsToData(i).toData())
        }
    }

    fun toData() = ObjectsToDateResponseData(
        objectsToDateResponseDomain.date,
        objectsToDateResponseDomain.progress,
        objects
    )
}