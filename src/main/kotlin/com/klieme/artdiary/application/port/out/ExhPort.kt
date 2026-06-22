package com.klieme.artdiary.application.port.out

import com.klieme.artdiary.domain.Exh
import java.time.LocalDate

interface ExhPort {
    fun findByKeyword(keyword: String): List<Exh>

    fun findByDate(date: LocalDate): List<Exh>

    fun findAll(): List<Exh>

    fun findByExhId(exhId: Long): Exh

    fun increaseLikeCount(exhId: Long)

    fun decreaseLikeCount(exhId: Long)

    fun existsByExhId(exhId: Long): Boolean
}
