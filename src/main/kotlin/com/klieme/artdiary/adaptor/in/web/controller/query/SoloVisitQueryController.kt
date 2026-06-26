package com.klieme.artdiary.adaptor.`in`.web.controller.query

import com.klieme.artdiary.adaptor.`in`.web.mapper.toListResponse
import com.klieme.artdiary.adaptor.`in`.web.response.*
import com.klieme.artdiary.application.port.`in`.query.SoloDiaryListQuery
import com.klieme.artdiary.application.port.`in`.query.SoloVisitQueryUseCase
import com.klieme.artdiary.infrastructure.jwt.CustomUserDetails
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "Solo Visit", description = "(Query) Solo Visit 개인 방문 API")
@RestController
@RequestMapping("/api/users/me/visited-exhibitions")
class SoloVisitQueryController(
    private val soloVisitQueryUseCase: SoloVisitQueryUseCase
) {
    @GetMapping
    fun getVisitedExhList(
        @AuthenticationPrincipal user: CustomUserDetails,
    ): ApiResult<List<SoloVisitedExhListResponse>> {
        val result = soloVisitQueryUseCase.getVisitedExhList(user.userId)
        val response = result.map { it.toListResponse() }

        return ApiResponse.get(response)
    }

    @GetMapping("/{exhId}/diaries")
    fun getSoloDiaryList(
        @PathVariable exhId: Long,
        @AuthenticationPrincipal user: CustomUserDetails,
    ): ApiResult<List<SoloDiaryListResponse>> {
        val query = SoloDiaryListQuery(
            exhId = exhId,
            userId = user.userId
        )
        val result = soloVisitQueryUseCase.getDiaryList(query)
        val response = result.toListResponse()

        return ApiResponse.get(response)
    }
}