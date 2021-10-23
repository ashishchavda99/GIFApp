package com.rabstract.core

/**
 * Result class is a wrapper class that helps to handle success and failure scenarios with co routines
 */

sealed class Result<out T> {

    data class Success<out T>(val successData : T) : Result<T>()
    class Error(val exception: java.lang.Exception, val message: String = exception.localizedMessage)
        : Result<Nothing>()

}