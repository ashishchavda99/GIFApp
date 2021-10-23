package com.rabstract.core

import com.rabstract.model.TrendingGif
import retrofit2.Response

object Utils {
    private fun <T : Any> handleApiError(resp: Response<T>): Result.Error {
        val error = ApiErrorUtils.parseError(resp)
        return Result.Error(Exception(error.message))
    }

    fun <T : Any> handleSuccess(response: Response<T>): Result<T> {
        response.body()?.let {
            return if(it is TrendingGif) {
                Result.Success(it)
            }else{
                returnError()
            }
        } ?: return handleApiError(response)
    }
}

private fun returnError(error: String? = "Something went wrong"): Result.Error {
    return Result.Error(Exception(error))
}