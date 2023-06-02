package com.example.domain.models

data class RegistrationParamsDomain(
    var fio : String = "",
    var login : String = "",
    var password : String = "",
    var email : String = "",
    var photoUrl : String = "",
    var countObject : Int = 0
)
