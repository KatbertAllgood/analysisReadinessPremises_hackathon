package com.example.data.mappers

import com.example.data.models.ScorParamData
import com.example.domain.models.ScorParamDomain

class ScorParamToData(
    private val scorParamDomain: ScorParamDomain
) {
    fun toData() = ScorParamData(
        scorParamDomain.name,
        scorParamDomain.accuracy,
        scorParamDomain.score
    )
}