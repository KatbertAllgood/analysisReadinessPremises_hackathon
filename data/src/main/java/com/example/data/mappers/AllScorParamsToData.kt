package com.example.data.mappers

import com.example.data.models.AllScorParamsData
import com.example.data.models.ScorParamData
import com.example.domain.models.AllScorParamsDomain

class AllScorParamsToData(
    private val allScorParamsDomain: AllScorParamsDomain
) {

    val clean : MutableList<ScorParamData> = mutableListOf()
    val rough : MutableList<ScorParamData> = mutableListOf()
    val other : MutableList<ScorParamData> = mutableListOf()

    init {
        for (i in allScorParamsDomain.clean) {
            clean.add(ScorParamToData(i).toData())
        }
        for (i in allScorParamsDomain.rough) {
            rough.add(ScorParamToData(i).toData())
        }
        for (i in allScorParamsDomain.other) {
            other.add(ScorParamToData(i).toData())
        }
    }

    fun toData() = AllScorParamsData(
        clean,
        rough,
        other
    )

}