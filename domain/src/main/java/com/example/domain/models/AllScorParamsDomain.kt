package com.example.domain.models

data class AllScorParamsDomain(
    val clean : List<ScorParamDomain> = listOf(),
    val rough : List<ScorParamDomain> = listOf(),
    val other : List<ScorParamDomain> = listOf(),
)