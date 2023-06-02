package com.example.data.models

import com.google.gson.annotations.SerializedName

data class SendVideoParamsResponseData(
    @SerializedName("Url_video")
    val urlVideo : String = "",
    @SerializedName("flatId")
    val flatId : Int = 0,
    @SerializedName("id_video")
    val idVideo : Int = 0,
    @SerializedName("sectionId")
    val sectionId : Int = 0
)
