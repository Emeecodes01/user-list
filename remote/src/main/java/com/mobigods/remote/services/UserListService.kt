package com.mobigods.remote.services

import com.mobigods.remote.models.BaseResponseModel
import com.mobigods.remote.models.UserRemoteModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface UserListService {

    @GET("data/api/user")
    suspend fun fetchUsers(@Query("limit") limit: Int = 100): BaseResponseModel<UserRemoteModel>

    @GET("data/api/user/{userId}")
    suspend fun fetchUser(@Path("userId") userId: String): UserRemoteModel
}
