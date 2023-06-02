package com.example.data.models

import com.google.gson.annotations.SerializedName

data class LoginParamsData(
    @SerializedName("login")
    var login : String = "",
    @SerializedName("password")
    var password : String = ""
)
