package com.example.data.mappers

import com.example.data.models.SendVideoParamsResponseData
import com.example.domain.models.SendVideoParamsResponseDomain

class SendVideoParamsResponseToDomain(
    private val sendVideoParamsResponseData : SendVideoParamsResponseData
) {
    fun toDomain() = SendVideoParamsResponseDomain(
        sendVideoParamsResponseData.urlVideo,
        sendVideoParamsResponseData.flatId,
        sendVideoParamsResponseData.idVideo,
        sendVideoParamsResponseData.sectionId,
    )
}