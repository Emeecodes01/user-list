package com.mobigods.domain.interactors.users

import com.google.common.truth.Truth.assertThat
import com.mobigods.domain.interactors.testutils.DataGenerator
import com.mobigods.domain.interactors.testutils.TestExecutionThreadImpl
import com.mobigods.domain.repository.remote.UserListRemoteRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.slot
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import java.lang.IllegalStateException

class GetUserRemoteUseCaseTest {

    private val executionThread = TestExecutionThreadImpl()

    @MockK
    lateinit var repository: UserListRemoteRepository

    private lateinit var getUserRemoteUseCase: GetUserRemoteUseCase

    companion object {
        const val USER_ID = "user-id"
    }


    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        getUserRemoteUseCase = GetUserRemoteUseCase(executionThread, repository)
    }


    @Test(expected = IllegalStateException::class)
    fun `throws an error when called with a null params`() = runBlockingTest {
        getUserRemoteUseCase.invoke(null)
    }


    @Test
    fun `verity its successful when called with the correct params`() = runBlockingTest {
        stubResponse()
        val slot = slot<String>()
        getUserRemoteUseCase.invoke(USER_ID)

        coVerify(exactly = 1) { repository.fetchUser(capture(slot)) }

        assertThat(slot.captured)
            .isEqualTo(USER_ID)
    }


    private fun stubResponse() {
        coEvery { repository.fetchUser(any()) } returns DataGenerator.generateUser()
    }
}