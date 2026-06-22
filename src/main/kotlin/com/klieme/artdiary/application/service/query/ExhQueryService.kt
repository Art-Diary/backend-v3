package com.klieme.artdiary.application.service.query

import com.klieme.artdiary.application.port.`in`.query.ExhQueryUseCase
import com.klieme.artdiary.application.port.out.ExhPort
import com.klieme.artdiary.domain.Exh
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class ExhQueryService(
    private val exhPort: ExhPort
): ExhQueryUseCase {
    override fun getExhList(keyword: String?, date: LocalDate?): List<Exh> {
        return if (keyword != null) {
            exhPort.findByKeyword(keyword)
        } else if (date != null) {
            exhPort.findByDate(date)
        } else {
            exhPort.findAll()
        }
    }

    override fun getExhDetail(exhId: Long): Exh {
        return exhPort.findByExhId(exhId)
    }
}
