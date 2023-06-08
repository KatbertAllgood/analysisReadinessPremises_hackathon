package com.example.domain.usecase.objects

import com.example.domain.repository.NetworkRepository

class GetAllObjectsUseCase(
    private val networkRepository: NetworkRepository
) {
    fun invoke() = networkRepository.getAllObjects()
}