package com.mobigods.userlist.viemodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobigods.domain.interactors.users.GetUserCachedUseCase
import com.mobigods.domain.interactors.users.GetUserRemoteUseCase
import com.mobigods.userlist.mappers.UserModelMapper
import com.mobigods.userlist.models.UserModel
import com.mobigods.userlist.ui.states.UserListResource
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class UserDetailFragmentViewModel @ViewModelInject constructor(
    private val getUserRemoteUseCase: GetUserRemoteUseCase,
    private val getUserCachedUseCase: GetUserCachedUseCase,
    private val userModelMapper: UserModelMapper
) : ViewModel() {

    private val _user: MutableLiveData<UserListResource<UserModel>> = MutableLiveData()
    val user: LiveData<UserListResource<UserModel>> = _user

    private val errorHandler = CoroutineExceptionHandler { _, throwable ->
        throwable.printStackTrace()
        _user.value = UserListResource.Error(throwable.message)
    }

    fun getUserDetail(userId: String) {
        getUserRemote(userId)

        viewModelScope.launch(errorHandler) {
            getUserCachedUseCase.execute(userId).collect { user ->
                user?.let {
                    _user.value = UserListResource.Success(userModelMapper.mapTo(user))
                }
            }
        }
    }

    private fun getUserRemote(userId: String) {
        viewModelScope.launch(errorHandler) {
            getUserRemoteUseCase.invoke(userId)
        }
    }
}
