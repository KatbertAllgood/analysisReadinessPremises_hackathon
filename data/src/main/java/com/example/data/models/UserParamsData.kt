package com.example.data.models

import com.google.gson.annotations.SerializedName

data class UserParamsData(
    @SerializedName("id")
    val id : Int = 0,
    @SerializedName("fio")
    val fio : String = "",
    @SerializedName("login")
    val login : String = "",
    @SerializedName("hash")
    val hash : String =  "",
    @SerializedName("email")
    val email : String = "",
    @SerializedName("photoUrl")
    val photoUrl : String = "",
    @SerializedName("count_objects")
    val count_objects : Int = 0,
    @SerializedName("createdAt")
    val createdAt : String = "",
    @SerializedName("updatedAt")
    val updatedAt : String = ""
)
