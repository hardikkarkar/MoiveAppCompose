package com.comet.movieapp.utils

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response

// Extension function on Response<T> to convert it to Result<T>
fun <T> Response<T>.toResult(): Result<T> {
    return try {
        if (isSuccessful) {
            val body = body()
            if (body != null) {
                Result.Success(body)
            } else {
                Result.Error(Exception("${code()} - Empty response body"))
            }
        } else {
            Result.Error( Exception(errorBody()?.string() ?: "Unknown server error"))
        }
    } catch (e: Exception) {
        Result.Error(Exception(errorBody()?.string() ?: "Unknown server error"))
    }
}

// Extension function on Response<T> to convert it to ResultOrThrow<T>
fun <T> Response<T>.getOrThrow(): T {
    return if (isSuccessful) {
        body() ?: throw Exception("${code()} - Empty response body")
    } else {
        throw Exception(errorBody()?.string() ?: "Unknown server error")
    }
}

fun <T: Any> Response<T>.toResultOrThrow() = Result.Success(getOrThrow())

// Extension function on Response<T> to convert it to ResultOrNull<T>
fun <T> Response<T>.getOrNull(): T? {
    return if (isSuccessful) {
        body()
    } else {
        null
    }
}

suspend fun <T> performNetworkFlow(networkCall: suspend () -> T): Flow<T> =
    flow {
        val responseStatus = networkCall.invoke()
        emit(responseStatus)
    }.flowOn(Dispatchers.IO)