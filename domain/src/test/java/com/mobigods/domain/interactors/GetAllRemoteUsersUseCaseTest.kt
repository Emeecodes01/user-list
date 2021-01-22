package com.mobigods.domain.interactors

import com.google.common.truth.Truth.*
import com.mobigods.domain.interactors.testutils.DataGenerator
import com.mobigods.domain.interactors.testutils.TestExecutionThreadImpl
import com.mobigods.domain.interactors.users.GetAllRemoteUsersUseCase
import com.mobigods.domain.models.User
import com.mobigods.domain.repository.remote.UserListRemoteRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before

import org.junit.Test

class GetAllRemoteUsersUseCaseTest {
    private val testImpl = TestExecutionThreadImpl()

    @MockK
    lateinit var remoteRepository: UserListRemoteRepository

    private lateinit var getAllRemoteUsersUseCase: GetAllRemoteUsersUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        getAllRemoteUsersUseCase = GetAllRemoteUsersUseCase(testImpl, remoteRepository)
    }


    @Test
    fun `returns list of users when invoke is called`() = runBlockingTest {
        stubResponse(DataGenerator.getFakeUsers(3))
        val users = getAllRemoteUsersUseCase.invoke()

        coVerify (exactly = 1) { remoteRepository.fetchUsers() }

        assertThat(users)
            .isNotEmpty()

        assertThat(users.size)
            .isEqualTo(3)
    }


    private fun stubResponse(fakeUsers: List<User>) {
        coEvery { remoteRepository.fetchUsers() } returns fakeUsers
    }

}