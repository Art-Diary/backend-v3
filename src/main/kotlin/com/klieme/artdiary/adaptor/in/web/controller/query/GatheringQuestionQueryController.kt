package com.klieme.artdiary.adaptor.`in`.web.controller.query

import com.klieme.artdiary.adaptor.`in`.web.mapper.toResponse
import com.klieme.artdiary.adaptor.`in`.web.response.ApiResponse
import com.klieme.artdiary.adaptor.`in`.web.response.ApiResult
import com.klieme.artdiary.adaptor.`in`.web.response.GatheringQuestionResponse
import com.klieme.artdiary.application.port.`in`.query.GatheringQuestionQuery
import com.klieme.artdiary.application.port.`in`.query.GatheringQuestionQueryUseCase
import com.klieme.artdiary.infrastructure.jwt.CustomUserDetails
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "Gathering Question", description = "(Query) Gathering Question 모임 질문 API")
@RestController
@RequestMapping("/api/gatherings/{gatheringId}/visits/{visitId}/questions")
class GatheringQuestionQueryController(
    private val gatheringQuestionQueryUseCase: GatheringQuestionQueryUseCase
) {
    @GetMapping
    fun getList(
        @PathVariable gatheringId: Long,
        @PathVariable visitId: Long,
        @AuthenticationPrincipal user: CustomUserDetails,
    ): ApiResult<List<GatheringQuestionResponse>> {
        val query = GatheringQuestionQuery(
            gatheringId = gatheringId,
            visitId = visitId,
            userId = user.userId
        )
        val list = gatheringQuestionQueryUseCase.getList(query)
        val response = list.map { it.toResponse() }

        return ApiResponse.get(response)
    }
}
