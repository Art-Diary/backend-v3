package com.klieme.artdiary.adaptor.`in`.web.controller.query

import com.klieme.artdiary.adaptor.`in`.web.mapper.toResponse
import com.klieme.artdiary.adaptor.`in`.web.response.ApiResponse
import com.klieme.artdiary.adaptor.`in`.web.response.ApiResult
import com.klieme.artdiary.adaptor.`in`.web.response.GatheringDetailResponse
import com.klieme.artdiary.adaptor.`in`.web.response.GatheringResponse
import com.klieme.artdiary.application.port.`in`.query.GatheringQuery
import com.klieme.artdiary.application.port.`in`.query.GatheringQueryUseCase
import com.klieme.artdiary.infrastructure.jwt.CustomUserDetails
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "Gathering", description = "(Query) Gathering 모임 API")
@RestController
@RequestMapping("/api/gatherings")
class GatheringQueryController(
    private val gatheringQueryUseCase: GatheringQueryUseCase
) {
    @GetMapping
    fun getList(
        @AuthenticationPrincipal user: CustomUserDetails,
    ): ApiResult<List<GatheringResponse>> {
        val list = gatheringQueryUseCase.getList(user.userId)
        val response = list.map { it.toResponse() }

        return ApiResponse.get(response)
    }

    @GetMapping("/{gatheringId}")
    fun getDetail(
        @PathVariable gatheringId: Long,
        @AuthenticationPrincipal user: CustomUserDetails,
    ): ApiResult<GatheringDetailResponse> {
        val query = GatheringQuery(
            gatheringId = gatheringId,
            userId = user.userId
        )
        val detail = gatheringQueryUseCase.getDetail(query)
        val response = detail.toResponse()

        return ApiResponse.get(response)
    }
}
