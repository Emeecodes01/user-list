package com.mobigods.domain.interactors.setup

import com.google.common.truth.Truth.assertThat
import com.mobigods.domain.interactors.testutils.TestExecutionThreadImpl
import com.mobigods.domain.repository.cache.IDataStoreManager
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test

class GetUserListSetUpUseCaseTest {

    private val executor = TestExecutionThreadImpl()

    @MockK
    lateinit var datastoreManager: IDataStoreManager

    private lateinit var getUserListSetUpUseCase: GetUserListSetUpUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        getUserListSetUpUseCase = GetUserListSetUpUseCase(executor, datastoreManager)
    }

    @Test
    fun `verify that invoke returns a boolean`() = runBlockingTest {
        stubResponse()
        val result = getUserListSetUpUseCase.invoke()

        assertThat(result)
            .isTrue()
    }

    private fun stubResponse() {
        coEvery { datastoreManager.isUserListSetUp } returns true
    }
}
