package com.klieme.artdiary.adaptor.`in`.web.controller.command

import com.klieme.artdiary.adaptor.`in`.web.request.SoloDiaryRequest
import com.klieme.artdiary.adaptor.`in`.web.request.SoloVisitRequest
import com.klieme.artdiary.adaptor.`in`.web.response.ApiResponse
import com.klieme.artdiary.application.port.`in`.command.*
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
    @PostMapping
    fun createVisit(
        @RequestBody request: SoloVisitRequest,
        @AuthenticationPrincipal user: CustomUserDetails
    ): ResponseEntity<Void> {
        val command = SoloVisitCreateCommand(
            exhId = request.exhId,
            userId = user.userId,
            visitDate = request.visitDate,
        )

        soloVisitCommandUseCase.createVisit(command)

        return ApiResponse.noContent()
    }

    @PostMapping("/{visitId}/diaries")
    fun createDiary(
        @PathVariable visitId: Long,
        @RequestBody request: List<SoloDiaryRequest>,
        @AuthenticationPrincipal user: CustomUserDetails
    ): ResponseEntity<Void> {
        val command = SoloDiaryCreateCommand(
            visitId = visitId,
            userId = user.userId,
            diaryList = request.map {
                DiaryListCommand(
                    questionId = it.questionId,
                    answerContent = it.answerContent,
                    isPublic = it.isPublic,
                )
            }
        )

        soloVisitCommandUseCase.createDiary(command)

        return ApiResponse.noContent()
    }

    @PatchMapping("/{visitId}/diaries/{soloDiaryId}")
    fun updateDiary(
        @PathVariable visitId: Long,
        @PathVariable soloDiaryId: Long,
        @RequestBody request: SoloDiaryRequest,
        @AuthenticationPrincipal user: CustomUserDetails
    ): ResponseEntity<Void> {
        val command = SoloDiaryUpdateCommand(
            visitId = visitId,
            userId = user.userId,
            soloDiaryId = soloDiaryId,
            questionId = request.questionId,
            answerContent = request.answerContent,
            isPublic = request.isPublic,
        )

        soloVisitCommandUseCase.updateDiary(command)

        return ApiResponse.noContent()
    }

    @DeleteMapping("/{visitId}/diaries/{soloDiaryId}")
    fun deleteDiary(
        @PathVariable visitId: Long,
        @PathVariable soloDiaryId: Long,
        @AuthenticationPrincipal user: CustomUserDetails
    ): ResponseEntity<Void> {
        val command = SoloDiaryDeleteCommand(
            visitId = visitId,
            userId = user.userId,
            soloDiaryId = soloDiaryId,
        )

        soloVisitCommandUseCase.deleteDiary(command)

        return ApiResponse.noContent()
    }
}
