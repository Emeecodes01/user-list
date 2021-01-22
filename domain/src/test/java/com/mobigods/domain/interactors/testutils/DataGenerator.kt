package com.mobigods.domain.interactors.testutils

import com.mobigods.domain.models.User
import konveyor.base.randomBuild

object DataGenerator {

    fun<T> generateList(count: Int, action: () -> T): List<T> {
        return mutableListOf<T>().apply {
            repeat(count) {
                add(action.invoke())
            }
        }
    }

    fun getFakeUsers(count: Int): List<User> {
        return generateList(count) { generateUser() }
    }

    fun generateUser(): User {
        return User(
            randomBuild(), randomBuild(), randomBuild(),
            randomBuild(), randomBuild(), randomBuild()
        )
    }

}