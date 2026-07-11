package com.klieme.artdiary.adaptor.`in`.web.controller.command

import com.klieme.artdiary.adaptor.`in`.web.response.ApiResponse
import com.klieme.artdiary.application.port.`in`.command.LikeExhCommand
import com.klieme.artdiary.application.port.`in`.command.LikeExhCommandUseCase
import com.klieme.artdiary.infrastructure.jwt.CustomUserDetails
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@Tag(name = "LikeExhibition", description = "LikeExhibition 좋아요 API")
@RestController
@RequestMapping("/api/exhibitions")
class LikeExhCommandController(
    private val likeExhCommandUseCase: LikeExhCommandUseCase
) {
    @PostMapping("/{exhId}/likes")
    fun like(
        @PathVariable exhId: Long,
        @AuthenticationPrincipal user: CustomUserDetails
    ): ResponseEntity<Void> {
        val likeExhCommand = LikeExhCommand(
            exhId = exhId,
            userId = user.userId
        )

        likeExhCommandUseCase.likeExecute(likeExhCommand)

        return ApiResponse.noContent()
    }

    @DeleteMapping("/{exhId}/likes")
    fun unlike(
        @PathVariable exhId: Long,
        @AuthenticationPrincipal user: CustomUserDetails
    ): ResponseEntity<Void> {
        val likeExhCommand = LikeExhCommand(
            exhId = exhId,
            userId = user.userId
        )

        likeExhCommandUseCase.unlikeExecute(likeExhCommand)

        return ApiResponse.noContent()
    }
}