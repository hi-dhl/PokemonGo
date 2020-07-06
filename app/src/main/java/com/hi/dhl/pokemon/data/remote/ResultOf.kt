package com.hi.dhl.pokemon.data.remote

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/7/6
 *     desc  : 处理成功或者失败的情况
 * </pre>
 */

sealed class ResultOf<out T> {
    data class Success<out R>(val value: R) : ResultOf<R>()
    data class Failure(
        val message: String?,
        val throwable: Throwable?
    ) : ResultOf<Nothing>()
}

inline fun <reified T> ResultOf<T>.doIfFailure(callback: (error: String?, throwable: Throwable?) -> Unit) {
    if (this is ResultOf.Failure) {
        callback(message, throwable)
    }
}

inline fun <reified T> ResultOf<T>.doIfSuccess(callback: (value: T) -> Unit) {
    if (this is ResultOf.Success) {
        callback(value)
    }
}