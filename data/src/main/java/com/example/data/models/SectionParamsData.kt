package com.example.data.models

import com.google.gson.annotations.SerializedName

data class SectionParamsData(
    @SerializedName("id_section")
    val id_section : Int = 0,
    @SerializedName("number_section")
    val number_section : Int = 0,
    @SerializedName("count_floor_house")
    val count_floor_house : Int = 0,
    @SerializedName("count_flat")
    val count_flat : Int = 0,
    @SerializedName("section_progress")
    val section_progress : Int = 0,
    @SerializedName("objectId")
    val objectId : Int = 0
)
