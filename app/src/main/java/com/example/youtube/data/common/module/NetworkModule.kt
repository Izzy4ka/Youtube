package com.example.youtube.data.common.module

import com.example.youtube.BuildConfig.BASE_URL
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {
    single { getOkHttpClient() }
    single { getRetrofit(get()) }
}

fun getOkHttpClient(): OkHttpClient =
    OkHttpClient.Builder().apply {
        writeTimeout(20, TimeUnit.SECONDS)
        connectTimeout(20, TimeUnit.SECONDS)
        readTimeout(20, TimeUnit.SECONDS)
    }.build()

fun getRetrofit(okHttpClient: OkHttpClient): Retrofit =
    Retrofit.Builder().apply {
        baseUrl(BASE_URL)
        addConverterFactory(GsonConverterFactory.create())
        client(okHttpClient)
    }.build()

