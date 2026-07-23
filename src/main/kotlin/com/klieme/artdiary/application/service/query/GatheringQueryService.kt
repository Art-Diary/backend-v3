package com.klieme.artdiary.application.service.query

import com.klieme.artdiary.application.dto.GatheringDetailDto
import com.klieme.artdiary.application.dto.GatheringListDto
import com.klieme.artdiary.application.dto.MemberListDto
import com.klieme.artdiary.application.port.`in`.query.GatheringQuery
import com.klieme.artdiary.application.port.`in`.query.GatheringQueryUseCase
import com.klieme.artdiary.application.port.out.GatheringPort
import com.klieme.artdiary.application.port.out.VisitPort
import org.springframework.stereotype.Service

@Service
class GatheringQueryService(
    private val gatheringPort: GatheringPort,
    private val visitPort: VisitPort
) : GatheringQueryUseCase {
    override fun getList(userId: Long): List<GatheringListDto> {
        val resultList = gatheringPort.findList(userId)

        val gatheringList = resultList
            .groupBy { Triple(it.id, it.name, it.exhibitionCount) }
            .map { (key, members) ->
                GatheringListDto(
                    id = key.first,
                    name = key.second,
                    exhibitionCount = key.third,
                    memberList = members
                        .take(3)
                        .map {
                            MemberListDto(
                                userId = it.userId,
                                nickname = it.nickname,
                                profile = it.profile
                            )
                        }
                )
            }

        return gatheringList
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
