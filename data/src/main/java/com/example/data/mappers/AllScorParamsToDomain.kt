package com.example.data.mappers

import com.example.data.models.AllScorParamsData
import com.example.data.models.ScorParamData
import com.example.domain.models.AllScorParamsDomain
import com.example.domain.models.ScorParamDomain

class AllScorParamsToDomain(
    private val allScorParamsData: AllScorParamsData
) {

    val clean : MutableList<ScorParamDomain> = mutableListOf()
    val rough : MutableList<ScorParamDomain> = mutableListOf()
    val other : MutableList<ScorParamDomain> = mutableListOf()

    init {
        for (i in allScorParamsData.clean) {
            clean.add(ScorParamToDomain(i).toDomain())
        }
        for (i in allScorParamsData.rough) {
            rough.add(ScorParamToDomain(i).toDomain())
        }
        for (i in allScorParamsData.other) {
            other.add(ScorParamToDomain(i).toDomain())
        }
    }

    fun toDomain() = AllScorParamsDomain(
        clean,
        rough,
        other
    )

}