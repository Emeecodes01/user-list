package com.mobigods.remote.interceptors

import android.provider.UserDictionary.Words.APP_ID
import com.mobigods.remote.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor: Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val newRequest = chain.request().newBuilder()
            .addHeader("app-id", BuildConfig.APP_ID)

        return chain.proceed(newRequest.build())
    }

}