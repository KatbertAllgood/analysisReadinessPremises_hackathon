package com.example.data.mappers

import com.example.data.models.ServerResponseMessageData
import com.example.domain.models.ServerResponseMessageDomain

class ServerResponseMessageToData(
    private val serverResponseMessageDomain: ServerResponseMessageDomain
) {
    fun toData() = ServerResponseMessageData(
        serverResponseMessageDomain.message
    )
}