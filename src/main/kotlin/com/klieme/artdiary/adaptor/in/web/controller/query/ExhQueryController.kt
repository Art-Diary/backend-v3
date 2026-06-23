package com.klieme.artdiary.adaptor.`in`.web.controller.query

import com.klieme.artdiary.adaptor.`in`.web.mapper.toDetailResponse
import com.klieme.artdiary.adaptor.`in`.web.mapper.toListResponse
import com.klieme.artdiary.adaptor.`in`.web.response.ApiResponse
import com.klieme.artdiary.adaptor.`in`.web.response.ApiResult
import com.klieme.artdiary.adaptor.`in`.web.response.ExhListResponse
import com.klieme.artdiary.adaptor.`in`.web.response.ExhDetailResponse
import com.klieme.artdiary.application.port.`in`.query.ExhDetailQuery
import com.klieme.artdiary.application.port.`in`.query.ExhListQuery
import com.klieme.artdiary.application.port.`in`.query.ExhQueryUseCase
import com.klieme.artdiary.common.exception.ArtdiaryException
import com.klieme.artdiary.common.exception.ErrorType
import com.klieme.artdiary.infrastructure.jwt.CustomUserDetails
import io.swagger.v3.oas.annotations.tags.Tag
import org.springdoc.core.annotations.ParameterObject
import org.springframework.data.domain.Slice
import org.springframework.data.web.PageableDefault
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.data.domain.Pageable
import java.time.LocalDate

@Tag(name = "Exhibition", description = "Exhibition 전시회 API")
@RestController
@RequestMapping("/api/exhibitions")
class ExhQueryController(
    private val exhQueryUseCase: ExhQueryUseCase
) {
    @GetMapping
    fun getExhList(
        @RequestParam(value = "keyword", required = false) keyword: String?,
        @RequestParam(value = "date", required = false) date: LocalDate?,
        @AuthenticationPrincipal user: CustomUserDetails,
        @ParameterObject
        @PageableDefault(size = 20) pageable: Pageable,
    ): ApiResult<Slice<ExhListResponse>> {
        if (keyword != null && date != null) {
            throw ArtdiaryException(ErrorType.BAD_REQUEST)
        }
        val exhListQuery = ExhListQuery(
            keyword = keyword,
            date = date,
            userId = user.userId,
            pageable = pageable,
        )
        val exhListResult = exhQueryUseCase.getExhList(exhListQuery)
        val response = exhListResult.map { it.toListResponse() }

        return ApiResponse.get(response)
    }

    @GetMapping("/{exhId}")
    fun getExhDetail(
        @PathVariable exhId: Long,
        @AuthenticationPrincipal user: CustomUserDetails
    ): ApiResult<ExhDetailResponse> {
        val exhDetailQuery = ExhDetailQuery(
            exhId = exhId,
            userId = user.userId
        )
        val exhResult = exhQueryUseCase.getExhDetail(exhDetailQuery)

        return ApiResponse.get(exhResult.toDetailResponse())
    }
}