package com.klieme.artdiary.adaptor.`in`.web.controller.command

import com.klieme.artdiary.adaptor.`in`.web.request.SoloVisitRequest
import com.klieme.artdiary.adaptor.`in`.web.response.ApiResponse
import com.klieme.artdiary.application.port.`in`.command.SoloVisitCreateCommand
import com.klieme.artdiary.application.port.`in`.command.SoloVisitCommandUseCase
import com.klieme.artdiary.application.port.`in`.command.SoloVisitDeleteCommand
import com.klieme.artdiary.application.port.`in`.command.SoloVisitUpdateCommand
import com.klieme.artdiary.infrastructure.jwt.CustomUserDetails
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@Tag(name = "Solo Visit", description = "(Command) Solo Visit 개인 방문 API")
@RestController
@RequestMapping("/api/users/me/visits")
class SoloVisitCommandController(
    private val soloVisitCommandUseCase: SoloVisitCommandUseCase
) {
    @PostMapping("/{visitId}/diaries")
    fun createDiary(
        @PathVariable visitId: Long,
        @RequestBody request: SoloVisitRequest,
        @AuthenticationPrincipal user: CustomUserDetails
    ): ResponseEntity<Void> {
        val command = SoloVisitCreateCommand(
            visitId = visitId,
            userId = user.userId,
            questionId = request.questionId,
            answerContent = request.answerContent,
            writeDate = request.writeDate,
            isPublic = request.isPublic,
        )

        soloVisitCommandUseCase.create(command)

        return ApiResponse.noContent()
    }

    @PatchMapping("/{visitId}/diaries/{soloDiaryId}")
    fun updateDiary(
        @PathVariable visitId: Long,
        @PathVariable soloDiaryId: Long,
        @RequestBody request: SoloVisitRequest,
        @AuthenticationPrincipal user: CustomUserDetails
    ): ResponseEntity<Void> {
        val command = SoloVisitUpdateCommand(
            visitId = visitId,
            userId = user.userId,
            soloDiaryId = soloDiaryId,
            questionId = request.questionId,
            answerContent = request.answerContent,
            writeDate = request.writeDate,
            isPublic = request.isPublic,
        )

        soloVisitCommandUseCase.update(command)

        return ApiResponse.noContent()
    }

    @DeleteMapping("/{visitId}/diaries/{soloDiaryId}")
    fun deleteDiary(
        @PathVariable visitId: Long,
        @PathVariable soloDiaryId: Long,
        @AuthenticationPrincipal user: CustomUserDetails
    ): ResponseEntity<Void> {
        val command = SoloVisitDeleteCommand(
            visitId = visitId,
            userId = user.userId,
            soloDiaryId = soloDiaryId,
        )

        soloVisitCommandUseCase.delete(command)

        return ApiResponse.noContent()
    }
}