package com.example.data.mappers

import com.example.data.models.ScorParamData
import com.example.domain.models.ScorParamDomain

class ScorParamToDomain(
    private val scorParamData: ScorParamData
) {
    fun toDomain() = ScorParamDomain(
        scorParamData.name,
        scorParamData.accuracy,
        scorParamData.score
    )
}