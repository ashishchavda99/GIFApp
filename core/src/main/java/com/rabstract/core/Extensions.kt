package com.rabstract.core

val Any.TAG: String
    get() {
        val tag = javaClass.simpleName
        return if (tag.length <= 23) tag else tag.substring(0, 23)
    }

fun noNetworkConnectivityError(): Result.Error {
    return Result.Error(Exception("Internet Not Available"))
}
fun noDataAvailableError(): Result.Error {
    return Result.Error(Exception("No Data Available"))
}
