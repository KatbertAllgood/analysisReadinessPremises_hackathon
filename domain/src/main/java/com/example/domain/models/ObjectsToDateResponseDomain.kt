package com.example.domain.models

data class ObjectsToDateResponseDomain(
    val date : String = "",
    val progress: Int = 0,
    val objects : List<ObjectParamsDomain> = listOf()
)