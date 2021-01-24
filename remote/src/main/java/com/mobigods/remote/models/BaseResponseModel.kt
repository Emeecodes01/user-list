package com.mobigods.remote.models

data class BaseResponseModel<T> (
    val data: List<T>,
    val total: Int,
    val page: Int,
    val limit: Int,
    val offset: Int
)
