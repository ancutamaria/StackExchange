package com.am.stackexchange.model

sealed class CustomResponse<out T> {

    data class OK<out V>(val data: V?): CustomResponse<V>()
    data class Error(val message: String): CustomResponse<Nothing>()

}