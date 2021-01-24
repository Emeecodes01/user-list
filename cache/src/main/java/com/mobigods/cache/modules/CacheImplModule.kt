package com.mobigods.cache.modules

import com.mobigods.cache.datastore.DataStoreManager
import com.mobigods.cache.impl.UserListCacheRepositoryImpl
import com.mobigods.domain.repository.cache.IDataStoreManager
import com.mobigods.domain.repository.cache.UserListCacheRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
interface CacheImplModule {

    @Singleton
    @Binds
    fun bindCacheRepoImpl(
        impl: UserListCacheRepositoryImpl
    ): UserListCacheRepository

    @Singleton
    @Binds
    fun bindPrefRepoImpl(
        impl: DataStoreManager
    ): IDataStoreManager
}
