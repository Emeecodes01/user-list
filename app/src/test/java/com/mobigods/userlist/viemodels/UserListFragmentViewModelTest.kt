package com.mobigods.userlist.viemodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.google.common.truth.Truth.assertThat
import com.mobigods.domain.interactors.setup.GetUserListSetUpUseCase
import com.mobigods.domain.interactors.users.GetAllCachedUsersUseCase
import com.mobigods.domain.interactors.users.GetAllRemoteUsersUseCase
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

class UserListFragmentViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private val dispatcher = TestCoroutineDispatcher()

    @MockK
    lateinit var getUserListUseCase: GetAllRemoteUsersUseCase

    @MockK
    lateinit var getAllCachedUsersUseCase: GetAllCachedUsersUseCase

    @MockK
    lateinit var getUserUserListSetUpUseCase: GetUserListSetUpUseCase

    private val userModelMapper = UserModelMapper(LocationModelMapper())

    private lateinit var userListViewModel: UserListFragmentViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        userListViewModel = UserListFragmentViewModel(
            getUserListUseCase,
            getAllCachedUsersUseCase,
            getUserUserListSetUpUseCase,
            userModelMapper
        )
        Dispatchers.setMain(dispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `getRemoteUsers emits loading when called`() = runBlockingTest {
        val users = AppDataGenerator.generateListOfUsers(5)
        stubUserResponse(users)
        val remoteUsersObserver = createRemoteUsersObserver()
        userListViewModel.userListRemote.observeForever(remoteUsersObserver)

        userListViewModel.getRemoteUsers()
        val slot = slot<UserListResource<Unit>>()
        verify { remoteUsersObserver.onChanged(capture(slot)) }
        slot.captured = UserListResource.Loading()

        assertThat(slot.captured.state)
            .isEqualTo(UserListState.LOADING)
    }

    @Test
    fun `getRemoteUsers emits succes when called`() = runBlockingTest {
        val users = AppDataGenerator.generateListOfUsers(5)
        stubUserResponse(users)
        val remoteUsersObserver = createRemoteUsersObserver()
        userListViewModel.userListRemote.observeForever(remoteUsersObserver)

        userListViewModel.getRemoteUsers()
        val slot = slot<UserListResource<Unit>>()
        verify { remoteUsersObserver.onChanged(capture(slot)) }

        assertThat(slot.captured.state)
            .isEqualTo(UserListState.SUCCESS)
    }

    @Test
    fun `getRemoteUsers emits failed when not successful`() = runBlockingTest {
        val users = AppDataGenerator.generateListOfUsers(5)
        stubUserResponse(users)
        val remoteUsersObserver = createRemoteUsersObserver()
        userListViewModel.userListRemote.observeForever(remoteUsersObserver)

        userListViewModel.getRemoteUsers()
        val slot = slot<UserListResource<Unit>>()
        verify { remoteUsersObserver.onChanged(capture(slot)) }
        slot.captured = UserListResource.Error("This is a error test")

        assertThat(slot.captured.state)
            .isEqualTo(UserListState.FAILED)
    }

    @Test
    fun `getCachedUsers emits success when successful`() = runBlockingTest {
        val users = AppDataGenerator.generateListOfUsers(5)
        stubCachedUserResponse(users)
        val cachedUsersObserver = createCachedUsersObserver()
        userListViewModel.userList.observeForever(cachedUsersObserver)

        userListViewModel.getCachedUsers()
        val slot = slot<UserListResource<List<UserModel>>>()
        verify { cachedUsersObserver.onChanged(capture(slot)) }

        assertThat(slot.captured.state)
            .isEqualTo(UserListState.SUCCESS)

        assertThat(slot.captured.data)
            .isNotNull()
    }

    @Test
    fun `getCachedUsers emits failed when there is an error`() = runBlockingTest {
        stubCachedUserErrorResponse()
        val cacheUsersObserver = createCachedUsersObserver()
        userListViewModel.userList.observeForever(cacheUsersObserver)

        userListViewModel.getCachedUsers()
        val slot = slot<UserListResource<List<UserModel>>>()

        verify { cacheUsersObserver.onChanged(capture(slot)) }

        assertThat(slot.captured.state)
            .isEqualTo(UserListState.FAILED)
    }

    private fun stubCachedUserResponse(users: List<User>) {
        coEvery { getAllCachedUsersUseCase.execute() } returns flowOf(users)
    }

    private fun stubCachedUserErrorResponse() {
        coEvery { getAllCachedUsersUseCase.execute() } throws Exception("test error")
    }

    private fun stubUserResponse(users: List<User>) {
        coEvery { getUserListUseCase.invoke() } returns users
    }

    private fun createRemoteUsersObserver(): Observer<UserListResource<Unit>> = spyk(Observer { })
    private fun createCachedUsersObserver(): Observer<UserListResource<List<UserModel>>> =
        spyk(Observer { })
}
