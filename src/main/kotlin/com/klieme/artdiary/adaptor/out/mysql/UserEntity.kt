package com.klieme.artdiary.adaptor.out.mysql

import com.klieme.artdiary.domain.ProviderType
import com.klieme.artdiary.domain.RoleType
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(
    name = "user",
    schema = "public",
    uniqueConstraints = [
        UniqueConstraint(name = "uk_user_nickname", columnNames = ["nickname"]),
        UniqueConstraint(name = "uk_user_provider", columnNames = ["provider_type", "provider_user_id"])
    ]
)
class UserEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    var userId: Long? = null,

    @Column(length = 50)
    var nickname: String? = null,

    @Column
    var profile: String? = null,

    @Column(name = "art_field")
    var artField: String? = null,

    @Enumerated(EnumType.STRING)
    @Column(name = "role_type", nullable = false, length = 10)
    var roleType: RoleType = RoleType.USER,

    @Enumerated(EnumType.STRING)
    @Column(name = "provider_type", nullable = false, length = 20)
    var providerType: ProviderType,

    @Column(name = "provider_user_id", nullable = false)
    var providerUserId: String,

    @Column(name = "refresh_token", length = 500)
    var refreshToken: String? = null,

    @Column(name = "refresh_token_expired_at")
    var refreshTokenExpiredAt: LocalDateTime? = null,

    @Column(name = "alarm_token")
    var alarmToken: String? = null,

    @Column(name = "deleted_at")
    var deletedAt: LocalDateTime? = null
) {
    // one many를 여기에 넣어??????? 왜????

    fun updateRefreshToken(
        refreshToken: String,
        refreshTokenExpiredAt: LocalDateTime
    ) {
        this.refreshToken = refreshToken
        this.refreshTokenExpiredAt = refreshTokenExpiredAt
    }
}
