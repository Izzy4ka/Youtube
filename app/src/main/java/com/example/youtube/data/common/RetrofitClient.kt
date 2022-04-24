package com.example.youtube.data.common

import com.example.youtube.BuildConfig.*
import com.example.youtube.data.playlists.remote.ApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitClient {
    companion object {
        fun create(): ApiService {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            val okHttpClient = OkHttpClient.Builder()
                .apply {
                    writeTimeout(20, TimeUnit.SECONDS)
                    readTimeout(20, TimeUnit.SECONDS)
                    addInterceptor(interceptor)
                }.build()
            val retrofit = Retrofit.Builder().apply {
                baseUrl(BASE_URL)
                addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
            }.build()
            return retrofit.create(ApiService::class.java)
        }
    }
}