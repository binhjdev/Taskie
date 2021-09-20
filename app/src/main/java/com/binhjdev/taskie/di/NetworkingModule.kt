package com.binhjdev.taskie.di

import com.binhjdev.taskie.data.api.TaskieApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://taskie-rw.herokuapp.com"
const val KEY_PREFERENCES = "taskie_preferences"
const val KEY_TOKEN = "token"

fun networkingModule() = module {
    single {
        OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }).build() // client
    }

    single {
        GsonConverterFactory.create() // gson
    }

    single {
        Retrofit.Builder()
                .addConverterFactory(get<GsonConverterFactory>())
                .client(get<OkHttpClient>())
                .baseUrl(BASE_URL)
                .build()
    }

    single {
        get<Retrofit>().create(TaskieApiService::class.java) // api service
    }
}
