package com.example.domain.usecase.score

import com.example.domain.models.ScorRequestDomain
import com.example.domain.repository.NetworkRepository

class GetScoreUseCase(
    private val networkRepository: NetworkRepository
) {
    fun invoke(path: ScorRequestDomain) = networkRepository.getScore(path)
}