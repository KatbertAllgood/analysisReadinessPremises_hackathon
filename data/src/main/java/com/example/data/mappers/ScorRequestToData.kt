package com.example.data.mappers

import com.example.data.models.ScorRequestData
import com.example.domain.models.ScorRequestDomain

class ScorRequestToData(
    private val scorRequestDomain : ScorRequestDomain
) {
    fun toData() = ScorRequestData(
        scorRequestDomain.path
    )
}