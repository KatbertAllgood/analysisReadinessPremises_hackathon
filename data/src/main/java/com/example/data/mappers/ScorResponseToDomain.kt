package com.example.data.mappers

import com.example.data.models.ScorResponseData
import com.example.domain.models.ScorResponseDomain

class ScorResponseToDomain(
    private val scorResponseData: ScorResponseData
) {
    fun toDomain() = ScorResponseDomain(
        scorResponseData.status_code,
        AllScorParamsToDomain(scorResponseData.data).toDomain()
    )
}