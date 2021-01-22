package com.mobigods.domain.interactors


import com.google.common.truth.Truth.*
import com.mobigods.domain.interactors.testutils.DataGenerator
import com.mobigods.domain.interactors.testutils.TestExecutionThreadImpl
import com.mobigods.domain.models.User
import com.mobigods.domain.repository.cache.UserListCacheRepository
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test

class SaveAllUsersUseCaseTest {

    private val testImpl = TestExecutionThreadImpl()

    @MockK
    lateinit var cacheRepository: UserListCacheRepository

    private lateinit var saveAllUsersUseCase: SaveAllUsersUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        saveAllUsersUseCase = SaveAllUsersUseCase(testImpl, cacheRepository)
    }


    @Test
    fun `verify that saveAllUsers is called when its invoked`() = runBlockingTest {
        stubResponse()
        val users = DataGenerator.getFakeUsers(4)
        saveAllUsersUseCase.invoke(users)

        coVerify(exactly = 1) { cacheRepository.saveAllUsers(any()) }
    }


    @Test
    fun `verify that saveAllUsers is called with the correct params`() = runBlockingTest {
        stubResponse()
        val users = DataGenerator.getFakeUsers(4)

        val slot = slot<List<User>>()
        saveAllUsersUseCase.invoke(users)

        coVerify { cacheRepository.saveAllUsers(capture(slot)) }

        assertThat(slot.captured[0])
            .isEqualTo(users[0])
    }


    private fun stubResponse() {
        coEvery { cacheRepository.saveAllUsers(any()) } returns listOf()
    }

}