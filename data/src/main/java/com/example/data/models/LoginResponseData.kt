package com.example.data.models

import com.google.gson.annotations.SerializedName

data class LoginResponseData(
    @SerializedName("token")
    val token : String = ""
)
