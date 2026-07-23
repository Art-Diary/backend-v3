package com.klieme.artdiary.application.port.`in`.query

import com.klieme.artdiary.application.dto.GatheringDetailDto
import com.klieme.artdiary.application.dto.GatheringListDto

interface GatheringQueryUseCase {
    fun getList(userId: Long): List<GatheringListDto>

    fun getDetail(query: GatheringQuery): GatheringDetailDto
}
