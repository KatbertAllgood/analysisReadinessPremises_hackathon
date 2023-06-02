package com.example.domain.models

data class UserParamsDomain(
    val id : Int = 0,
    val fio : String = "",
    val login : String = "",
    val hash : String =  "",
    val email : String = "",
    val photoUrl : String = "",
    val count_objects : Int = 0,
    val createdAt : String = "",
    val updatedAt : String = ""
)
