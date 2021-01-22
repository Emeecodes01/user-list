package com.mobigods.remote.modules

import com.mobigods.remote.BuildConfig
import com.mobigods.remote.utils.retrofit
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
class RetrofitModule {

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        gson: GsonConverterFactory
    ): Retrofit {
        return retrofit {
            baseUrl(BuildConfig.BASE_URL)
            addConverterFactory(gson)
            client(okHttpClient)
        }
    }

}