package com.example.domain.models

data class ObjectsToDateResponseDomain(
    val date : String = "",
    val objects : List<ObjectParamsDomain> = listOf()
)