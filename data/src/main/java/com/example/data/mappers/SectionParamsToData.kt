package com.example.data.mappers

import com.example.data.models.SectionParamsData
import com.example.domain.models.SectionParamsDomain

class SectionParamsToData(
    private val sectionParamsDomain: SectionParamsDomain
) {
    fun toData() = SectionParamsData(
        sectionParamsDomain.id_section,
        sectionParamsDomain.number_section,
        sectionParamsDomain.count_floor_house,
        sectionParamsDomain.count_flat,
        sectionParamsDomain.section_progress,
        sectionParamsDomain.objectId,
    )
}