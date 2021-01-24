package com.mobigods.cache.db.dao

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.google.common.truth.Truth.assertThat
import com.mobigods.cache.db.AppDatabase
import com.mobigods.cache.testutils.CacheDataGenerator
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class UserDaoTest {

    private lateinit var testDb: AppDatabase
    private lateinit var userDao: UserDao

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        testDb = Room.inMemoryDatabaseBuilder(
            context, AppDatabase::class.java
        ).build()

        userDao = testDb.userDao()
    }

    @Test
    fun verify_saveUser_actually_saves_a_user() = runBlocking {
        val user = CacheDataGenerator.generateUserCache()
        val id = userDao.saveUser(user)

        assertThat(id)
            .isGreaterThan(0)
    }

    @Test
    fun verify_that_saveAllUsers_saves_a_list_of_users() = runBlocking {
        val users = CacheDataGenerator.generateUserCacheList(5)
        val ids = userDao.saveAllUsers(users)

        assertThat(ids)
            .isNotEmpty()
    }

    @Test
    fun verify_that_getAllUsers_returns_all_users() = runBlocking {
        val users = CacheDataGenerator.generateUserCacheList(5)
        userDao.saveAllUsers(users)

        val usersReturned = userDao.getAllUsers().first()

        assertThat(usersReturned[0])
            .isEqualTo(users[0])
    }

    @Test
    fun verify_that_get_a_user_returns_a_user() = runBlocking {
        val user = CacheDataGenerator.generateUserCache()
        userDao.saveUser(user)

        val savedUser = userDao.getUser(user.id)
        assertThat(savedUser)
            .isEqualTo(user)
    }
}
