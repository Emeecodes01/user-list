package com.mobigods.userlist.viemodels.testutils

import com.mobigods.domain.models.User
import konveyor.base.randomBuild

object AppDataGenerator {

    fun generateUser(): User = User(
        randomBuild(), randomBuild(), randomBuild(),
        randomBuild(), randomBuild(), randomBuild()
    )

    fun generateListOfUsers(count: Int): List<User> {
        val list = mutableListOf<User>()
        repeat(count) {
            list.add(generateUser())
        }
        return list
    }
}
