package com.klieme.artdiary.adaptor.out.mysql

import com.klieme.artdiary.domain.Exh

object ExhMapper {
    fun toEntity(domain: Exh): ExhEntity =
        ExhEntity(
            exhId = domain.exhId,
            exhName = domain.exhName,
            gallery = domain.gallery,
            startDate = domain.startDate,
            endDate = domain.endDate,
            painter = domain.painter,
            fee = domain.fee,
            intro = domain.intro,
            homepageLink = domain.homepageLink,
            poster = domain.poster,
            artField = domain.artField,
            source = domain.source
        )

    fun toDomain(entity: ExhEntity): Exh =
        Exh(
            exhId = entity.exhId,
            exhName = entity.exhName,
            gallery = entity.gallery,
            startDate = entity.startDate,
            endDate = entity.endDate,
            painter = entity.painter,
            fee = entity.fee,
            intro = entity.intro,
            homepageLink = entity.homepageLink,
            poster = entity.poster,
            artField = entity.artField,
            source = entity.source
        )
}
