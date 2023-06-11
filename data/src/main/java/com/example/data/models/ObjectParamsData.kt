package com.example.data.models

import com.google.gson.annotations.SerializedName

data class ObjectParamsData(
    @SerializedName("id_object")
    val id_object : Int = 0,
    @SerializedName("name")
    val name : String = "",
    @SerializedName("count_section")
    val count_section : Int = 0,
    @SerializedName("count_flats")
    val count_flats : Int = 0,
    @SerializedName("userId")
    val userId : Int = 0,
    @SerializedName("date")
    val date : String = "",
    @SerializedName("street")
    val street : String = "",
    @SerializedName("building")
    val building : String = "",
    @SerializedName("general_readiness")
    val general_readiness : Int = 0,
    @SerializedName("ready_section")
    val ready_section : Int = 0
)
