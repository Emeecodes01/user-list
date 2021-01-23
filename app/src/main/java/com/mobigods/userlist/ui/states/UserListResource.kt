package com.mobigods.userlist.ui.states

sealed class UserListResource<T>(
    val state: UserListState,
    val data: T? = null,
    val message: String? = null
) {

    class Success<T>(data: T) : UserListResource<T>(UserListState.SUCCESS, data)
    class Loading<T>(data: T? = null) : UserListResource<T>(UserListState.LOADING, data)
    class Error<T>(message: String?, data: T? = null) : UserListResource<T>(UserListState.FAILED, data, message)
}