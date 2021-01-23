package com.mobigods.remote.impl

import com.google.common.truth.Truth
import com.google.common.truth.Truth.*
import com.mobigods.domain.models.User
import com.mobigods.domain.repository.cache.IDataStoreManager
import com.mobigods.domain.repository.cache.UserListCacheRepository
import com.mobigods.remote.mappers.LocationRemoteMapper
import com.mobigods.remote.mappers.UserRemoteModelMapper
import com.mobigods.remote.models.BaseResponseModel
import com.mobigods.remote.models.UserRemoteModel
import com.mobigods.remote.services.UserListService
import com.mobigods.remote.testutils.RemoteDataFactory
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test

class UserListRemoteImplTest {

    @MockK
    lateinit var service: UserListService

    @MockK
    lateinit var cache: UserListCacheRepository

    @MockK
    lateinit var dataStore: IDataStoreManager

    private val mapper = UserRemoteModelMapper(LocationRemoteMapper())

    private lateinit var userListRemoteImpl: UserListRemoteImpl

    companion object {
        const val USER_ID = "user-id"
    }

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        userListRemoteImpl = UserListRemoteImpl(service, cache, dataStore, mapper)
    }

    @Test
    fun `verify that fetchUsers returns a list of users`() = runBlockingTest {
        val response = RemoteDataFactory.getResponseModelFake()
        stubResponse(response)

        val users = userListRemoteImpl.fetchUsers()

        assertThat(users.size)
            .isEqualTo(10)
    }



    @Test
    fun `verify that fetchUser returns a list of users`() = runBlockingTest {
        val user = RemoteDataFactory.getUser()
        stubUserResponse(user)

        val userResult = userListRemoteImpl.fetchUser(USER_ID)

        assertThat(userResult)
            .isEqualTo(mapper.mapFrom(user))
    }

    private fun stubUserResponse(user: UserRemoteModel) {
        coEvery { service.fetchUser(any()) } returns user
    }

    private fun stubResponse(response: BaseResponseModel<UserRemoteModel>) {
        coEvery { service.fetchUsers() } returns response
        coEvery { cache.saveAllUsers(any()) } returns listOf()
    }

}