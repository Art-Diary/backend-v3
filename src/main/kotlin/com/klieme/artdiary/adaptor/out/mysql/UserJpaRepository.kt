package com.klieme.artdiary.adaptor.out.mysql

import com.klieme.artdiary.domain.ProviderType
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserJpaRepository: JpaRepository<UserEntity, Long> {
    fun findByProviderTypeAndProviderUserId(
        providerType: ProviderType,
        providerUserId: String
    ): UserEntity?

    fun findByUserId(userId: Long): UserEntity?
}
