package com.klieme.artdiary.adaptor.`in`.web.controller.query

import com.klieme.artdiary.adaptor.`in`.web.mapper.toResponse
import com.klieme.artdiary.adaptor.`in`.web.response.ApiResponse
import com.klieme.artdiary.adaptor.`in`.web.response.ApiResult
import com.klieme.artdiary.adaptor.`in`.web.response.GatheringMessageResponse
import com.klieme.artdiary.application.port.`in`.query.GatheringMessageQuery
import com.klieme.artdiary.application.port.`in`.query.GatheringMessageQueryUseCase
import com.klieme.artdiary.infrastructure.jwt.CustomUserDetails
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "Gathering Message", description = "(Query) Gathering Message 모임 대화 API")
@RestController
@RequestMapping("/api/gatherings/{gatheringId}/visits/{visitId}/questions/{questionId}/messages")
class GatheringMessageQueryController(
    private val gatheringMessageQueryUseCase: GatheringMessageQueryUseCase
) {
    @GetMapping
    fun getList(
        @PathVariable gatheringId: Long,
        @PathVariable visitId: Long,
        @PathVariable questionId: Long,
        @AuthenticationPrincipal user: CustomUserDetails
    ): ApiResult<List<GatheringMessageResponse>> {
        val query = GatheringMessageQuery(
            gatheringId = gatheringId,
            visitId = visitId,
            questionId = questionId,
            userId = user.userId
        )
        val list = gatheringMessageQueryUseCase.getList(query)
        val response = list.map { it.toResponse() }

        return ApiResponse.get(response)
    }
}
