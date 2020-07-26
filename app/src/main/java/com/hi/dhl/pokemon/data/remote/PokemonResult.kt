/*
 * Copyright 2020. hi-dhl (Jack Deng)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hi.dhl.pokemon.data.remote

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/7/11
 *     desc  : 处理成功或者失败的情况
 * </pre>
 */

sealed class PokemonResult<out T> {
    data class Success<out T>(val value: T) : PokemonResult<T>()

    data class Failure(val throwable: Throwable?) : PokemonResult<Nothing>()
}

inline fun <reified T> PokemonResult<T>.doSuccess(success: (T) -> Unit) {
    if (this is PokemonResult.Success) {
        success(value)
    }
}

inline fun <reified T> PokemonResult<T>.doFailure(failure: (Throwable?) -> Unit) {
    if (this is PokemonResult.Failure) {
        failure(throwable)
    }
}
