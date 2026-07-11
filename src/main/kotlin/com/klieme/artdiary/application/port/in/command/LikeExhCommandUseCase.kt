package com.klieme.artdiary.application.port.`in`.command

interface LikeExhCommandUseCase {

    fun likeExecute(command: LikeExhCommand)

    fun unlikeExecute(command: LikeExhCommand)
}