package com.example.data.models

import com.google.gson.annotations.SerializedName

data class ObjectParamsData(
    @SerializedName("id_object")
    val id_object : Int = 0,
    @SerializedName("name")
    val name : String = "",
    @SerializedName("address")
    val address : String = "",
    @SerializedName("count_section")
    val count_section : Int = 0,
    @SerializedName("count_flats")
    val count_flats : Int = 0
)
