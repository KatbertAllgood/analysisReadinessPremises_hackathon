package com.example.data.models

import com.google.gson.annotations.SerializedName

data class ScorRequestData(
    @SerializedName("path")
    val path : String = ""
)
