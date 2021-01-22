package com.mobigods.remote.testutils

import com.mobigods.domain.models.User
import com.mobigods.remote.models.BaseResponseModel
import com.mobigods.remote.models.UserRemoteModel
import konveyor.base.randomBuild

object RemoteDataFactory {

    fun getUsers(count: Int): List<UserRemoteModel> {
        val list: MutableList<UserRemoteModel> = mutableListOf()
        repeat(count) {
            list.add(getUser())
        }
        return list
    }

    fun getResponseModelFake(): BaseResponseModel<UserRemoteModel>{
        return BaseResponseModel(
            data = getUsers(10),
            total = 100,
            page = 1,
            limit = 500,
            offset = 2
        )
    }


    fun getUser() = UserRemoteModel(
        randomBuild(), randomBuild(), randomBuild(),
        randomBuild(), randomBuild(), randomBuild(),
        randomBuild(), randomBuild(), randomBuild(),
        randomBuild()
    )
}