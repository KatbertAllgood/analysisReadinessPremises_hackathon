package com.example.domain.usecase.section

import com.example.domain.repository.NetworkRepository

class GetAllSectionsUseCase(
    private val networkRepository: NetworkRepository
) {
    fun invoke() = networkRepository.getAllSections()
}