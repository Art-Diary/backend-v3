package com.klieme.artdiary.adaptor.`in`.web.response

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

typealias ApiResult<T> = ResponseEntity<ApiResponse<T>>

class ApiResponse<T>(
    val status: HttpStatus,
    val data: T? = null,
    val errorMessage: String? = null
) {
    private fun toResponse(): ApiResult<T> = ResponseEntity(this, status)

    companion object {
        fun <T> get(data: T): ApiResult<T> =
            ApiResponse(status = HttpStatus.OK, data = data).toResponse()

        fun <T> created(data: T): ApiResult<T> =
            ApiResponse(status = HttpStatus.CREATED, data = data).toResponse()

        fun failure(errorMessage: String, status: HttpStatus): ApiResult<Nothing> =
            ApiResponse<Nothing>(status = status, errorMessage = errorMessage).toResponse()

    }
}
