package com.binhjdev.taskie.di

import com.binhjdev.taskie.contextProvider.CoroutineContextProvider
import com.binhjdev.taskie.contextProvider.CoroutineContextProviderImpl
import com.binhjdev.taskie.ui.addNewTask.AddNewTaskPresenterImpl
import com.binhjdev.taskie.ui.login.LoginPresenterImpl
import com.binhjdev.taskie.ui.main.TasksPresenterImpl
import com.binhjdev.taskie.ui.main.dialog.DeleteTaskPresenter
import com.binhjdev.taskie.ui.main.dialog.TaskDeletePresenterImpl
import com.binhjdev.taskie.ui.profile.ProfilePresenterImpl
import com.binhjdev.taskie.ui.register.RegisterPresenterImpl
import kotlinx.coroutines.Dispatchers
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

fun presenterModule() = module {
    single { CoroutineContextProviderImpl(Dispatchers.IO) as CoroutineContextProvider }
    viewModel { TasksPresenterImpl(get()) }
    viewModel { RegisterPresenterImpl(get()) }
    viewModel { LoginPresenterImpl(get()) }
    viewModel { AddNewTaskPresenterImpl(get()) }
    viewModel { ProfilePresenterImpl(get()) }
    viewModel { TaskDeletePresenterImpl(get()) }
}