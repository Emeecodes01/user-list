package com.mobigods.userlist.viemodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobigods.domain.interactors.setup.GetUserListSetUpUseCase
import com.mobigods.domain.interactors.users.GetAllCachedUsersUseCase
import com.mobigods.domain.interactors.users.GetAllRemoteUsersUseCase
import com.mobigods.userlist.mappers.UserModelMapper
import com.mobigods.userlist.models.UserModel
import com.mobigods.userlist.ui.states.UserListResource
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class UserListFragmentViewModel @ViewModelInject constructor(
    private val getUserListUseCase: GetAllRemoteUsersUseCase,
    private val getAllCachedUsersUseCase: GetAllCachedUsersUseCase,
    private val getUserUserListSetUpUseCase: GetUserListSetUpUseCase,
    private val userModelMapper: UserModelMapper
): ViewModel() {

    private val _userListRemote: MutableLiveData<UserListResource<Unit>> = MutableLiveData()
    val userListRemote: LiveData<UserListResource<Unit>> = _userListRemote


    private val _userList: MutableLiveData<UserListResource<List<UserModel>>> = MutableLiveData()
    val userList: LiveData<UserListResource<List<UserModel>>> = _userList

    private val errorHandler = CoroutineExceptionHandler { _, throwable ->
        throwable.printStackTrace()
        _userListRemote.value = UserListResource.Error(throwable.message)
    }


    fun getUsers() {
        getCachedUsers()
        viewModelScope.launch (errorHandler) {
            if (!getUserUserListSetUpUseCase.invoke())
                getRemoteUsers()
        }
    }


    private fun getCachedUsers() {
        viewModelScope.launch {
            getAllCachedUsersUseCase.execute().collect {
                _userList.value = UserListResource.Success(it.map { user -> userModelMapper.mapTo(user) })
            }
        }
    }



    private suspend fun getRemoteUsers() {
        _userListRemote.value = UserListResource.Loading()
         getUserListUseCase.invoke()
        _userListRemote.value = UserListResource.Success(Unit)
    }


}