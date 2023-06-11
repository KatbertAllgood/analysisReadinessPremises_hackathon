package com.example.data.models

import com.google.gson.annotations.SerializedName

data class ScorParamData(
    @SerializedName("name")
    val name : String = "",
    @SerializedName("accuracy")
    val accuracy : Double = 0.0,
    @SerializedName("score")
    val score : String = ""
)
