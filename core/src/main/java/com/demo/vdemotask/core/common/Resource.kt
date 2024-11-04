package com.demo.vdemotask.core.common

/**
 * A generic class that holds a value with its loading status.
 * @param <T>
</T> */
sealed class Resource<out T> {
    data class Success<out T>(val data: T) : Resource<T>()
    data class Error(val exception: Exception) : Resource<Nothing>()
    object Loading : Resource<Nothing>()
    class InitScreen<T> : Resource<T>()

}