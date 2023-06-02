package com.example.data.mappers

import com.example.data.models.ServerResponseMessageData
import com.example.domain.models.ServerResponseMessageDomain

class ServerResponseMessageToDomain(
    private val serverResponseMessageData : ServerResponseMessageData
) {
    fun toDomain() = ServerResponseMessageDomain(
        serverResponseMessageData.message
    )
}