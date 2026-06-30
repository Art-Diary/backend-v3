package com.klieme.artdiary.application.port.`in`.query

import com.klieme.artdiary.application.dto.GatheringDetailDto
import com.klieme.artdiary.domain.Gathering

interface GatheringQueryUseCase {
    fun getList(userId: Long): List<Gathering>

    fun getDetail(query: GatheringQuery): GatheringDetailDto
}
