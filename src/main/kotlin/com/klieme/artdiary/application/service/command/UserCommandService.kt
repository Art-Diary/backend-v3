package com.klieme.artdiary.application.service.command

import com.klieme.artdiary.adaptor.`in`.web.response.TokenResponse
import com.klieme.artdiary.application.port.`in`.command.UserCommand
import com.klieme.artdiary.application.port.`in`.command.UserCommandUseCase
import com.klieme.artdiary.application.port.out.UserPort
import com.klieme.artdiary.domain.User
import com.klieme.artdiary.infrastructure.jwt.JwtProvider
import jakarta.transaction.Transactional
import mu.KotlinLogging
import org.springframework.stereotype.Service
import java.time.LocalDateTime

private val logger = KotlinLogging.logger {}

@Service
@Transactional
class UserCommandService(
    private val userPort: UserPort,
    private val jwtProvider: JwtProvider
): UserCommandUseCase {
    private val adjectives = listOf(
        "고요한", "따뜻한", "푸른", "반짝이는",
        "은은한", "포근한", "조용한", "맑은"
    )

    private val nouns = listOf(
        "캔버스", "스케치", "물감", "노을",
        "별빛", "구름", "화실", "기록"
    )

    fun generateNickname(): String {
        val nickname = "${adjectives.random()}${nouns.random()}"
        val number = (100..999).random()

        return "$nickname$number"
    }

    override fun login(command: UserCommand): TokenResponse {
        // 회원가입 여부 확인
        val user = userPort.findByProvider(
            command.providerType,
            command.providerUserId
        )?: run {
            var nickname: String

            do {
                nickname = generateNickname()
            } while (userPort.existsByNickname(nickname))

            val newUser = User(
                nickname = nickname,
                providerType = command.providerType,
                providerUserId = command.providerUserId,
            )

            userPort.save(newUser)
        }

        val userId = requireNotNull(user.userId)

        val accessToken = jwtProvider.createAccessToken(userId)
        val refreshToken = jwtProvider.createRefreshToken(userId)

        logger.info { "accessToken: $accessToken" }

        userPort.updateRefreshToken(
            userId = userId,
            refreshToken = refreshToken,
            refreshTokenExpiredAt = LocalDateTime.now().plusDays(14),
            alarmToken = command.alarmToken
        )

        return TokenResponse(
            accessToken = accessToken,
            refreshToken = refreshToken
        )
    }
}
