package com.binhjdev.taskie.di

import com.binhjdev.taskie.domain.repository.TaskRepository
import com.binhjdev.taskie.domain.repository.TaskRepositoryImpl
import org.koin.dsl.module

fun appModule() = module {
    single { TaskRepositoryImpl(get()) as TaskRepository }
}