package com.binhjdev.taskie.contextProvider

import kotlin.coroutines.CoroutineContext

interface CoroutineContextProvider {
    fun context() : CoroutineContext
}