package com.mobigods.domain.interactors

import com.google.common.truth.Truth.assertThat
import com.mobigods.domain.interactors.testutils.DataGenerator
import com.mobigods.domain.interactors.testutils.TestExecutionThreadImpl
import com.mobigods.domain.interactors.users.GetAllCachedUsersUseCase
import com.mobigods.domain.models.User
import com.mobigods.domain.repository.cache.UserListCacheRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test

class GetAllCachedUsersUseCaseTest {
    private val testImpl = TestExecutionThreadImpl()

    @MockK
    lateinit var cacheRepository: UserListCacheRepository

    private lateinit var getAllCachedUsersUseCase: GetAllCachedUsersUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        getAllCachedUsersUseCase = GetAllCachedUsersUseCase(testImpl, cacheRepository)
    }

    @Test
    fun `verify that when execute is called cacheRepository is called`() {
        stubResponse(DataGenerator.getFakeUsers(5))
        getAllCachedUsersUseCase.execute()

        verify(exactly = 1) { cacheRepository.getAllUsers() }
    }

    @Test
    fun `verify that execute returns the correct values`() = runBlockingTest {
        val users = DataGenerator.getFakeUsers(5)
        stubResponse(users)
        val result = getAllCachedUsersUseCase.execute().first()

        assertThat(result.size)
            .isEqualTo(5)

        assertThat(result[0])
            .isEqualTo(users[0])
    }

    private fun stubResponse(users: List<User>) {
        coEvery { cacheRepository.getAllUsers() } returns flowOf(users)
    }
}
