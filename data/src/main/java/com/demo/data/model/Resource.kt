package com.demo.data.model


sealed class Resource<T>{

    class InitScreen<T> : Resource<T>()
    class SessionExpire<T> : Resource<T>()
    class Loading<T> : Resource<T>()
    // We'll wrap our data in this 'Success'
    // class in case of success response from api
    class Success<T>(val data: T) : Resource<T>()

    // We'll pass error message wrapped in this 'Error'
    // class to the UI in case of failure response
    class Error<T>(val errorMessage: String,val errorCode: String) :
        Resource<T>()

    // We'll just pass object of this Loading
    // class, just before making an api call

    class OfflineSuccess<T>(val errorMessage: String) : Resource<T>()
}