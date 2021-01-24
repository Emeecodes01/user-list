package com.mobigods.cache.testutils

import com.mobigods.cache.models.LocationCacheModel
import com.mobigods.cache.models.UserCacheModel
import konveyor.base.randomBuild

object CacheDataGenerator {

    fun generateUserCacheList(count: Int): List<UserCacheModel> {
        val list = mutableListOf<UserCacheModel>()
        repeat(count) {
            list.add(generateUserCache())
        }
        return list
    }

    fun generateUserCache(): UserCacheModel {
        return UserCacheModel(
            randomBuild(), randomBuild(), randomBuild(),
            randomBuild(), randomBuild(), randomBuild(),
            randomBuild(), randomBuild(), randomBuild(),
            randomBuild(LocationCacheModel::class.java)
        )
    }
}
