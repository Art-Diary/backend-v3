package com.klieme.artdiary.application.port.`in`.query

import com.klieme.artdiary.domain.Exh
import java.time.LocalDate

interface ExhQueryUseCase {
    fun getExhList(keyword: String?, date: LocalDate?): List<Exh>
    fun getExhDetail(exhId: Long): Exh
}
