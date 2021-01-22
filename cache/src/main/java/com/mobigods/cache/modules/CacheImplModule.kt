package com.mobigods.cache.modules

import com.mobigods.cache.impl.UserListCacheRepositoryImpl
import com.mobigods.domain.repository.cache.UserListCacheRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
abstract class CacheImplModule {

    @Provides
    @Singleton
    abstract fun bindCacheRepoImpl(
        impl: UserListCacheRepositoryImpl
    ): UserListCacheRepository

}