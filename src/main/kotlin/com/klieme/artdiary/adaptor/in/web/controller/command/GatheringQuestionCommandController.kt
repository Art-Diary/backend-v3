package com.klieme.artdiary.adaptor.`in`.web.controller.command

import com.klieme.artdiary.adaptor.`in`.web.request.GatheringQuestionRequest
import com.klieme.artdiary.adaptor.`in`.web.response.ApiResponse
import com.klieme.artdiary.application.port.`in`.command.GatheringQuestionCommandUseCase
import com.klieme.artdiary.application.port.`in`.command.GatheringQuestionCreateCommand
import com.klieme.artdiary.application.port.`in`.command.GatheringQuestionUpdateCommand
import com.klieme.artdiary.infrastructure.jwt.CustomUserDetails
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@Tag(name = "Gathering Question", description = "(Command) Gathering Question 모임 질문 API")
@RestController
@RequestMapping("/api/gatherings/{gatheringId}/visits/{visitId}/questions")
class GatheringQuestionCommandController(
    private val gatheringQuestionCommandUseCase: GatheringQuestionCommandUseCase
) {
    @PostMapping
    fun create(
        @RequestBody request: GatheringQuestionRequest,
        @PathVariable gatheringId: Long,
        @PathVariable visitId: Long,
        @AuthenticationPrincipal user: CustomUserDetails
    ): ResponseEntity<Void> {
        val command = GatheringQuestionCreateCommand(
            gatheringId = gatheringId,
            visitId = visitId,
            userId = user.userId,
            content = request.content
        )

        gatheringQuestionCommandUseCase.create(command)

        return ApiResponse.noContent()
    }

    @PatchMapping("/{questionId}")
    fun update(
        @RequestBody request: GatheringQuestionRequest,
        @PathVariable gatheringId: Long,
        @PathVariable visitId: Long,
        @PathVariable questionId: Long,
        @AuthenticationPrincipal user: CustomUserDetails
    ): ResponseEntity<Void> {
        val command = GatheringQuestionUpdateCommand(
            gatheringId = gatheringId,
            visitId = visitId,
            userId = user.userId,
            questionId = questionId,
            content = request.content
        )

        gatheringQuestionCommandUseCase.update(command)

        return ApiResponse.noContent()
    }
}
