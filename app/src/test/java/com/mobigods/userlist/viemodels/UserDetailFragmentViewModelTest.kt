package com.mobigods.userlist.viemodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.google.common.truth.Truth.assertThat
import com.mobigods.domain.interactors.users.GetUserCachedUseCase
import com.mobigods.domain.interactors.users.GetUserRemoteUseCase
import com.mobigods.domain.models.User
import com.mobigods.userlist.mappers.LocationModelMapper
import com.mobigods.userlist.mappers.UserModelMapper
import com.mobigods.userlist.models.UserModel
import com.mobigods.userlist.ui.states.UserListResource
import com.mobigods.userlist.ui.states.UserListState
import com.mobigods.userlist.viemodels.testutils.AppDataGenerator
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.just
import io.mockk.runs
import io.mockk.slot
import io.mockk.spyk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class UserDetailFragmentViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private val dispatcher = TestCoroutineDispatcher()

    @MockK
    lateinit var getUserRemoteUseCase: GetUserRemoteUseCase

    @MockK
    lateinit var getUserCachedUseCase: GetUserCachedUseCase

    private val userModelMapper = UserModelMapper(LocationModelMapper())

    private lateinit var userDetailFragmentViewModel: UserDetailFragmentViewModel

    companion object {
        const val USER_ID = "user-id"
    }

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        userDetailFragmentViewModel =
            UserDetailFragmentViewModel(getUserRemoteUseCase, getUserCachedUseCase, userModelMapper)
        Dispatchers.setMain(dispatcher)
    }

    @Test
    fun `verify that getUserDetail returns success`() = runBlockingTest {
        val user = AppDataGenerator.generateUser()
        stubUserResponse(user)
        val usersDetailObserver = createUsersDetailObserver()
        userDetailFragmentViewModel.user.observeForever(usersDetailObserver)

        userDetailFragmentViewModel.getUserDetail(USER_ID)

        val slot = slot<UserListResource<UserModel>>()
        verify { usersDetailObserver.onChanged(capture(slot)) }

        assertThat(slot.captured.state)
            .isEqualTo(UserListState.SUCCESS)

        assertThat(slot.captured.data)
            .isNotNull()
    }

    @Test
    fun `verify that getUserDetail returns failure when not successful`() = runBlockingTest {
        stubErrorUserDetailResponse()
        val usersDetailObserver = createUsersDetailObserver()
        userDetailFragmentViewModel.user.observeForever(usersDetailObserver)

        userDetailFragmentViewModel.getUserDetail(USER_ID)

        val slot = slot<UserListResource<UserModel>>()
        verify { usersDetailObserver.onChanged(capture(slot)) }

        assertThat(slot.captured.state)
            .isEqualTo(UserListState.FAILED)

        assertThat(slot.captured.data)
            .isNull()

        assertThat(slot.captured.message)
            .isNotEmpty()
    }

    private fun stubUserResponse(user: User) {
        coEvery { getUserCachedUseCase.execute(any()) } returns flowOf(user)
        coEvery { getUserRemoteUseCase.invoke() } just runs
    }

    private fun stubErrorUserDetailResponse() {
        coEvery { getUserCachedUseCase.execute(any()) } throws Exception("Test error")
        coEvery { getUserRemoteUseCase.invoke() } just runs
    }

    private fun createUsersDetailObserver(): Observer<UserListResource<UserModel>> =
        spyk(Observer { })

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}
