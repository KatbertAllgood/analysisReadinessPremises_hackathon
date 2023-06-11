package com.example.data.models

import com.google.gson.annotations.SerializedName

data class AllScorParamsData(
    @SerializedName("clean")
    val clean : List<ScorParamData> = listOf(),
    @SerializedName("rough")
    val rough : List<ScorParamData> = listOf(),
    @SerializedName("other")
    val other : List<ScorParamData> = listOf(),
)
