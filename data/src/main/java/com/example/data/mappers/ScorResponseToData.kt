package com.example.data.mappers

import com.example.data.models.ScorResponseData
import com.example.domain.models.ScorResponseDomain

class ScorResponseToData(
    private val scorResponseDomain: ScorResponseDomain
) {
    fun toData() = ScorResponseData(
        scorResponseDomain.status_code,
        AllScorParamsToData(scorResponseDomain.data).toData()
    )
}