package com.mobigods.remote.modules

import com.mobigods.remote.services.UserListService
import com.mobigods.remote.utils.service
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton
import retrofit2.Retrofit

@Module
@InstallIn(ApplicationComponent::class)
class ServiceModule {

    @Provides
    @Singleton
    fun provideAuthServiceService(retrofit: Retrofit):
            UserListService {
        return service(retrofit)
    }
}
