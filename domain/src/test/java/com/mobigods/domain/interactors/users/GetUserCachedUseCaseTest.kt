package com.mobigods.domain.interactors.users

import com.google.common.truth.Truth.assertThat
import com.mobigods.domain.interactors.testutils.DataGenerator
import com.mobigods.domain.interactors.testutils.TestExecutionThreadImpl
import com.mobigods.domain.models.User
import com.mobigods.domain.repository.cache.UserListCacheRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import java.lang.IllegalStateException

class GetUserCachedUseCaseTest {

    private val executionThread = TestExecutionThreadImpl()

    @MockK
    lateinit var repository: UserListCacheRepository

    private lateinit var getUserCachedUseCase: GetUserCachedUseCase

    companion object {
        const val USER_ID = "user-id"
    }


    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        getUserCachedUseCase = GetUserCachedUseCase(executionThread, repository)
    }

    @Test(expected = IllegalStateException::class)
    fun `throws and error when called with a null param`() = runBlockingTest {
        val fakeUser = DataGenerator.generateUser()
        stubResponse(fakeUser)
        getUserCachedUseCase.execute(null)
    }


    @Test
    fun `verify that execute returns a user`() = runBlockingTest {
        val fakeUser = DataGenerator.generateUser()
        stubResponse(fakeUser)
        val user = getUserCachedUseCase.execute(USER_ID)

        assertThat(user.first())
            .isEqualTo(fakeUser)
    }

    private fun stubResponse(fakeUser: User) {
        coEvery { repository.getUser(any()) } returns flowOf(fakeUser)
    }


}