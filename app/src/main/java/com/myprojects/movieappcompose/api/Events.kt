package com.myprojects.movieappcompose.api

sealed class Events<T>(val data: T? = null, val msg: String? = null) {
    class Success<T>(data: T?) : Events<T>(data)

    class Loading<T>(data: T? = null) : Events<T>(data)

    class Error<T>(data: T? = null, msg: String? = null) : Events<T>(data, msg)
}
