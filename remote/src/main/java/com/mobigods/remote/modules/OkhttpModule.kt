package com.mobigods.remote.modules

import android.content.Context
import com.mobigods.remote.interceptors.AuthInterceptor
import com.mobigods.remote.utils.okhttp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

@Module
@InstallIn(ApplicationComponent::class)
class OkhttpModule {

    @Provides
    @Singleton
    fun provideCache(@ApplicationContext context: Context): Cache {
        val cacheDir = context.cacheDir
        return Cache(cacheDir, 5 * 1000 * 1000)
    }

    @Provides
    @Singleton
    fun provideOkhttp(
        cache: Cache,
        loggingInterceptor: HttpLoggingInterceptor,
        authInterceptor: AuthInterceptor
    ): OkHttpClient {
        return okhttp {
            addInterceptor(loggingInterceptor)
            addInterceptor(authInterceptor)
            connectTimeout(60L, TimeUnit.SECONDS)
            callTimeout(60L, TimeUnit.SECONDS)
            cache(cache)
        }
    }
}
