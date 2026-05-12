package com.klieme.artdiary.adaptor.out.mysql

import com.klieme.artdiary.domain.User

object UserMapper {
    fun toEntity(domain: User): UserEntity =
        UserEntity(
            userId = domain.userId,
            nickname = domain.nickname,
            profile = domain.profile,
            artField = domain.artField,
            roleType = domain.roleType,
            providerType = domain.providerType,
            providerUserId = domain.providerUserId,
            refreshToken = domain.refreshToken,
            refreshTokenExpiredAt = domain.refreshTokenExpiredAt,
            alarmToken = domain.alarmToken,
            deletedAt = domain.deletedAt
        )

    fun toDomain(entity: UserEntity): User =
        User(
            userId = entity.userId,
            nickname = entity.nickname,
            profile = entity.profile,
            artField = entity.artField,
            roleType = entity.roleType,
            providerType = entity.providerType,
            providerUserId = entity.providerUserId,
            refreshToken = entity.refreshToken,
            refreshTokenExpiredAt = entity.refreshTokenExpiredAt,
            alarmToken = entity.alarmToken,
            deletedAt = entity.deletedAt
        )
}
