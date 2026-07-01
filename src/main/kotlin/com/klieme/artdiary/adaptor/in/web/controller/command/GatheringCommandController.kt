package com.klieme.artdiary.adaptor.`in`.web.controller.command

import com.klieme.artdiary.adaptor.`in`.web.request.GatheringCreateRequest
import com.klieme.artdiary.adaptor.`in`.web.request.GatheringJoinRequest
import com.klieme.artdiary.adaptor.`in`.web.request.GatheringVisitRequest
import com.klieme.artdiary.adaptor.`in`.web.response.ApiResponse
import com.klieme.artdiary.application.port.`in`.command.GatheringCommandUseCase
import com.klieme.artdiary.application.port.`in`.command.GatheringCreateCommand
import com.klieme.artdiary.application.port.`in`.command.GatheringJoinCommand
import com.klieme.artdiary.application.port.`in`.command.GatheringVisitExhCommand
import com.klieme.artdiary.infrastructure.jwt.CustomUserDetails
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@Tag(name = "Gathering", description = "(Command) Gathering 모임 API")
@RestController
@RequestMapping("/api/gatherings")
class GatheringCommandController(
    private val gatheringCommandUseCase: GatheringCommandUseCase
) {
    @PostMapping
    fun create(
        @RequestBody request: GatheringCreateRequest,
        @AuthenticationPrincipal user: CustomUserDetails
    ): ResponseEntity<Void> {
        val command = GatheringCreateCommand(
            userId = user.userId,
            name = request.name
        )
        gatheringCommandUseCase.create(command)

        return ApiResponse.noContent()
    }

    @PostMapping("/join")
    fun join(
        @RequestBody request: GatheringJoinRequest,
        @AuthenticationPrincipal user: CustomUserDetails
    ): ResponseEntity<Void> {
        val command = GatheringJoinCommand(
            userId = user.userId,
            code = request.code
        )

        gatheringCommandUseCase.join(command)

        return ApiResponse.noContent()
    }

    @PostMapping("/{gatheringId}/visits")
    fun visitExh(
        @PathVariable gatheringId: Long,
        @RequestBody request: GatheringVisitRequest,
        @AuthenticationPrincipal user: CustomUserDetails
    ): ResponseEntity<Void> {
        val command = GatheringVisitExhCommand(
            userId = user.userId,
            gatheringId = gatheringId,
            exhId = request.exhId,
            visitDate = request.visitDate
        )

        gatheringCommandUseCase.visitExh(command)

        return ApiResponse.noContent()
    }
}
