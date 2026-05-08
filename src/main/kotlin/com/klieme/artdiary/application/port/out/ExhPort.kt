package com.klieme.artdiary.application.port.out

import com.klieme.artdiary.adaptor.out.mysql.ExhEntity

interface ExhPort {
    fun findByExhId(exhId: Long): ExhEntity
}
