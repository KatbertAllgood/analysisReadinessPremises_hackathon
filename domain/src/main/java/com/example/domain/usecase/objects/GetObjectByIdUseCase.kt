package com.example.domain.usecase.objects

import com.example.domain.repository.NetworkRepository

class GetObjectByIdUseCase(
    private val networkRepository: NetworkRepository
) {
    fun invoke(
        id: String
    ) = networkRepository.getObjectById(id)
}