package com.example.data.models

import com.google.gson.annotations.SerializedName

data class RegistrationParamsData(
    @SerializedName("fio")
    var fio : String = "",
    @SerializedName("login")
    var login : String = "",
    @SerializedName("password")
    var password : String = "",
    @SerializedName("email")
    var email : String = "",
    @SerializedName("photoUrl")
    var photoUrl : String = "",
    @SerializedName("count_object")
    var countObject : Int = 0
)