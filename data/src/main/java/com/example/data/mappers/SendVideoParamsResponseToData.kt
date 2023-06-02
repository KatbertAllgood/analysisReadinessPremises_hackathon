package com.example.data.mappers

import com.example.data.models.SendVideoParamsResponseData
import com.example.domain.models.SendVideoParamsResponseDomain

class SendVideoParamsResponseToData(
    private val sendVideoParamsResponseDomain : SendVideoParamsResponseDomain
) {
    fun toData() = SendVideoParamsResponseData(
        sendVideoParamsResponseDomain.urlVideo,
        sendVideoParamsResponseDomain.flatId,
        sendVideoParamsResponseDomain.idVideo,
        sendVideoParamsResponseDomain.sectionId,

    )
}