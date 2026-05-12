package com.klieme.artdiary.application.port.out

import com.klieme.artdiary.domain.Exh

interface ExhPort {
    fun findByExhId(exhId: Long): Exh
}
