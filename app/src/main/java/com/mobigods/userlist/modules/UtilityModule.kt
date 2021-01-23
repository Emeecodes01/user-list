package com.mobigods.userlist.modules

import com.mobigods.domain.thread.ExecutionThread
import com.mobigods.domain.thread.ExecutionThreadImpl
import com.mobigods.userlist.utils.ImageLoader
import com.mobigods.userlist.utils.ImageLoaderImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
abstract class UtilityModule {

    @Singleton
    @Binds
    abstract fun bindExecutorImpl(
        impl: ExecutionThreadImpl
    ): ExecutionThread

    @Singleton
    @Binds
    abstract fun bindImageLoaderImpl(
        impl: ImageLoaderImpl
    ): ImageLoader

}