package com.klieme.artdiary.common.exception

class ArtdiaryException (
    val errorType: ErrorType,
    override val message: String = errorType.message
) : RuntimeException(message)
