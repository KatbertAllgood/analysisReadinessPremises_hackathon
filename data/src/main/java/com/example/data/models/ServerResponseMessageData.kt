package com.example.data.models

import com.google.gson.annotations.SerializedName

data class ServerResponseMessageData(
    @SerializedName("message")
    val message : String = ""
)
