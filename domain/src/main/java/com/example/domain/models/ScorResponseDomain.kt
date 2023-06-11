package com.example.domain.models

data class ScorResponseDomain(
    val status_code : Int = 0,
    val data : AllScorParamsDomain = AllScorParamsDomain()
)
