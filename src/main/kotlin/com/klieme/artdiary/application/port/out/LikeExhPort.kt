package com.klieme.artdiary.application.port.out

interface LikeExhPort {
    fun exists(exhId: Long, userId: Long): Boolean

    fun save(exhId: Long, userId: Long)

    fun delete(exhId: Long, userId: Long): Long
}