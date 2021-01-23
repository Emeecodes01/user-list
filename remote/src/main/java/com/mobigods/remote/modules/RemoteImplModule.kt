package com.mobigods.remote.modules


import com.mobigods.domain.repository.remote.UserListRemoteRepository
import com.mobigods.remote.impl.UserListRemoteImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
abstract class RemoteImplModule {

    @Binds
    @Singleton
    abstract fun bindSubjectImpl(
        impl: UserListRemoteImpl
    ): UserListRemoteRepository


}