package com.klieme.artdiary.application.service.query

import com.klieme.artdiary.application.dto.GatheringDetailDto
import com.klieme.artdiary.application.port.`in`.query.GatheringQuery
import com.klieme.artdiary.application.port.`in`.query.GatheringQueryUseCase
import com.klieme.artdiary.application.port.out.GatheringPort
import com.klieme.artdiary.application.port.out.VisitPort
import com.klieme.artdiary.domain.Gathering
import org.springframework.stereotype.Service

@Service
class GatheringQueryService(
    private val gatheringPort: GatheringPort,
    private val visitPort: VisitPort
) : GatheringQueryUseCase {
    override fun getList(userId: Long): List<Gathering> {
        return gatheringPort.findList(userId)
    }

    override fun getDetail(query: GatheringQuery): GatheringDetailDto {
        // 모임 조회: select gathering from gathering where gatheringId = id
        val gathering = gatheringPort.findById(query.gatheringId)
        // 모임 멤버 조회: select user from gahteringMember where gatheringId = id
        val gatheringMember = gatheringPort.findMemberList(query.gatheringId, query.userId)
        // 방문 전시회 목록 조회: select exh, visitdate from visit join exh where gatheringId = id
        val visitedExhList = visitPort.findGatheringVisitedExhList(query.gatheringId)

        return GatheringDetailDto(
            gathering = gathering,
            visitedExhList = visitedExhList,
            gatheringMemberList = gatheringMember
        )
    }
}
