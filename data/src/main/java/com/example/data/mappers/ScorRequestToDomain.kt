package com.example.data.mappers

import com.example.data.models.ScorRequestData
import com.example.domain.models.ScorRequestDomain

class ScorRequestToDomain(
    private val scorRequestData: ScorRequestData
) {
    fun toDomain() = ScorRequestDomain(
        scorRequestData.path
    )
}