package com.example.data.models

import com.google.gson.annotations.SerializedName

data class ScorResponseData(
    @SerializedName("status_code")
    val status_code : Int = 0,
    @SerializedName("data")
    val data : AllScorParamsData = AllScorParamsData()
)
