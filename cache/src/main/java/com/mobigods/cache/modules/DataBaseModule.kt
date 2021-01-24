package com.mobigods.cache.modules

import android.content.Context
import com.mobigods.cache.db.AppDatabase
import com.mobigods.cache.utils.constants.CacheConstants
import com.mobigods.cache.utils.database
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class DataBaseModule {

    @Provides
    @Singleton
    fun provideUseFormsDatabase(@ApplicationContext context: Context): AppDatabase =
        database(context, CacheConstants.DATABASE_NAME) {}
}
