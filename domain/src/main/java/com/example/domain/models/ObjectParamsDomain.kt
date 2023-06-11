package com.example.domain.models

data class ObjectParamsDomain(
    val id_object : Int = 0,
    val name : String = "",
    val count_section : Int = 0,
    val count_flats : Int = 0,
    val userId : Int = 0,
    val date : String = "",
    val street : String = "",
    val building : String = "",
    val general_readiness : Int = 0,
    val ready_section : Int = 0
)
