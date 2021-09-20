package com.binhjdev.taskie.data.model

/**
 * Represents either a value or a throwable, from an API response
 */
sealed class Result<out T : Any>

data class Success<out T : Any>(val data : T) : Result<T>()

data class Failure(val error : Throwable?)  : Result<Nothing>()