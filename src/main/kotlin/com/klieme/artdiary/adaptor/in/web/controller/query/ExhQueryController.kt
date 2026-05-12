package com.klieme.artdiary.adaptor.`in`.web.controller.query

import com.klieme.artdiary.adaptor.`in`.web.response.ApiResponse
import com.klieme.artdiary.adaptor.`in`.web.response.ApiResult
import com.klieme.artdiary.application.port.`in`.query.ExhQueryUseCase
import com.klieme.artdiary.domain.Exh
import com.klieme.artdiary.infrastructure.jwt.CustomUserDetails
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate

data class ExhResponse(
    val exhId: Long?,
    val exhName: String,
    val gallery: String,
    val startDate: LocalDate,
    val endDate: LocalDate,
    val painter: String?,
    val fee: Int,
    val intro: String?,
    val homepageLink: String?,
    val poster: String,
    val artField: String?,
    val source: String
)

@Tag(name = "Exhibition", description = "Exhibition 전시회 API")
@RestController
@RequestMapping("/api/exhibitions")
class ExhQueryController(
    private val exhQueryUseCase: ExhQueryUseCase
) {
    @GetMapping("/{exhId}")
    fun get(
        @PathVariable exhId: Long,
        @AuthenticationPrincipal user: CustomUserDetails
    ): ApiResult<ExhResponse> {
        val exh = exhQueryUseCase.get(exhId)
        return ApiResponse.get(exh.toResponse())
    }
}

private fun Exh.toResponse(): ExhResponse = ExhResponse(
    exhId = exhId,
    exhName = exhName,
    gallery = gallery,
    startDate = startDate,
    endDate = endDate,
    painter = painter,
    fee = fee,
    intro = intro,
    homepageLink = homepageLink,
    poster = poster,
    artField = artField,
    source = source
)
