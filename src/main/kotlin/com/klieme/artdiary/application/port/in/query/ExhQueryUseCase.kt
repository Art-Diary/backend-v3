package com.klieme.artdiary.application.port.`in`.query

import com.klieme.artdiary.domain.Exh

interface ExhQueryUseCase {
    fun get(exhId: Long): Exh
}
