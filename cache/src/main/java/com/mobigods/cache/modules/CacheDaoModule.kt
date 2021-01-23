package com.mobigods.cache.modules

import com.mobigods.cache.db.AppDatabase
import com.mobigods.cache.db.dao.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
class CacheDaoModule {

    @Provides
    @Singleton
    fun provideUserDao(database: AppDatabase): UserDao
    = database.userDao()
}