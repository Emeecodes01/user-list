package com.mobigods.remote.modules

import android.content.Context
import com.mobigods.remote.utils.okhttp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class OkhttpModule {

    @Provides
    @Singleton
    fun provideCache(context: Context): Cache {
        val cacheDir = context.cacheDir
        return Cache(cacheDir, 5 * 1000 * 1000)
    }


    @Provides
    @Singleton
    fun provideOkhttp(
        cache: Cache,
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return okhttp {
            addInterceptor(loggingInterceptor)
            connectTimeout(60L, TimeUnit.SECONDS)
            callTimeout(60L, TimeUnit.SECONDS)
            cache(cache)
        }
    }

}