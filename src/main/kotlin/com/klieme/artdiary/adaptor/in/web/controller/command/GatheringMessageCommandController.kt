package com.klieme.artdiary.adaptor.`in`.web.controller.command

import com.klieme.artdiary.adaptor.`in`.web.request.GatheringMessageRequest
import com.klieme.artdiary.adaptor.`in`.web.response.ApiResponse
import com.klieme.artdiary.application.port.`in`.command.GatheringMessageCommandUseCase
import com.klieme.artdiary.application.port.`in`.command.GatheringMessageCreateCommand
import com.klieme.artdiary.application.port.`in`.command.GatheringMessageDeleteCommand
import com.klieme.artdiary.application.port.`in`.command.GatheringMessageUpdateCommand
import com.klieme.artdiary.infrastructure.jwt.CustomUserDetails
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@Tag(name = "Gathering Message", description = "(Command) Gathering Message 모임 대화 API")
@RestController
@RequestMapping("/api")
class GatheringMessageCommandController(
    private val gatheringMessageCommandUseCase: GatheringMessageCommandUseCase
) {
    @PostMapping("/gatherings/{gatheringId}/visits/{visitId}/questions/{questionId}/messages")
    fun create(
        @RequestBody request: GatheringMessageRequest,
        @PathVariable gatheringId: Long,
        @PathVariable visitId: Long,
        @PathVariable questionId: Long,
        @AuthenticationPrincipal user: CustomUserDetails
    ): ResponseEntity<Void> {
        val command = GatheringMessageCreateCommand(
            gatheringId = gatheringId,
            visitId = visitId,
            questionId = questionId,
            userId = user.userId,
            content = request.content
        )

        gatheringMessageCommandUseCase.create(command)

        return ApiResponse.noContent()
    }

    @PatchMapping("/messages/{messageId}")
    fun update(
        @RequestBody request: GatheringMessageRequest,
        @PathVariable messageId: Long,
        @AuthenticationPrincipal user: CustomUserDetails
    ): ResponseEntity<Void> {
        val command = GatheringMessageUpdateCommand(
            messageId = messageId,
            userId = user.userId,
            content = request.content
        )

        gatheringMessageCommandUseCase.update(command)

        return ApiResponse.noContent()
    }

    @DeleteMapping("/messages/{messageId}")
    fun delete(
        @PathVariable messageId: Long,
        @AuthenticationPrincipal user: CustomUserDetails
    ): ResponseEntity<Void> {
        val command = GatheringMessageDeleteCommand(
            messageId = messageId,
            userId = user.userId
        )

        gatheringMessageCommandUseCase.delete(command)

        return ApiResponse.noContent()
    }
}
