package com.example.data.mappers

import com.example.data.models.SectionParamsData
import com.example.domain.models.SectionParamsDomain

class SectionParamsToDomain(
    private val sectionParamsData: SectionParamsData
) {
    fun toDomain() = SectionParamsDomain(
        sectionParamsData.id_section,
        sectionParamsData.number_section,
        sectionParamsData.count_floor_house,
        sectionParamsData.count_flat,
        sectionParamsData.section_progress,
        sectionParamsData.objectId,
    )
}