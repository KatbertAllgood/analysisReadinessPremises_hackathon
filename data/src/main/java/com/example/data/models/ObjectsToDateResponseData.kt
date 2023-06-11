package com.example.data.models

import com.google.gson.annotations.SerializedName

data class ObjectsToDateResponseData(
    @SerializedName("date")
    val date : String = "",
    @SerializedName("progress")
    val progress : Int = 0,
    @SerializedName("objects")
    val objects : List<ObjectParamsData> = listOf()
)
