package com.example.data.models

import com.google.gson.annotations.SerializedName

data class ObjectsToDateResponseData(
    @SerializedName("date")
    val date : String = "",
    @SerializedName("object")
    val objects : List<ObjectParamsData> = listOf()
)
