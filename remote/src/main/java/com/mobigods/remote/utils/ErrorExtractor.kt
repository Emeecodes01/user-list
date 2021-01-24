package com.mobigods.remote.utils

import retrofit2.HttpException

object ErrorExtractor {

    fun getReadableMessage(err: Throwable) =
        when (err) {
            is HttpException -> ""
            // todo add other error here
            else -> err.message ?: "An error has occurred"
        }
}
