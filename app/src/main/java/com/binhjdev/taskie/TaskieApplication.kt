package com.binhjdev.taskie

import android.app.Application
import android.content.Context
import com.binhjdev.taskie.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class TaskieApplication : Application() {

    companion object {
        private lateinit var instance: TaskieApplication

        private val preferences by lazy {
            instance.getSharedPreferences(KEY_PREFERENCES, Context.MODE_PRIVATE)
        }

        fun saveToken(token: String) {
            preferences.edit()
                    .putString(KEY_TOKEN, token)
                    .apply()
        }

        fun getToken() = preferences.getString(KEY_TOKEN, "") ?: ""
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        startKoin {
            androidContext(this@TaskieApplication)
            modules(listOf(appModule(), networkingModule(), presenterModule()))
        }
    }
}