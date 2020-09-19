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

package com.hi.dhl.pokemon.ui.detail

import androidx.databinding.ObservableBoolean
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.hi.dhl.pokemon.data.remote.doFailure
import com.hi.dhl.pokemon.data.remote.doSuccess
import com.hi.dhl.pokemon.data.repository.Repository
import com.hi.dhl.pokemon.model.PokemonInfoModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/7/11
 *     desc  :
 * </pre>
 */

@FlowPreview
@ExperimentalCoroutinesApi
class DetailViewModel @ViewModelInject constructor(
    private val pokemonRepository: Repository
) : ViewModel() {
    val mLoading = ObservableBoolean()

    // 私有的 MutableLiveData 可变的，对内访问
    private val _pokemon = MutableLiveData<PokemonInfoModel>()

    // 对外暴露不可变的 LiveData，只能查询
    val pokemon: LiveData<PokemonInfoModel> = _pokemon

    private val _failure = MutableLiveData<String>()
    val failure = _failure

    /**
     * 方法二
     */
    fun fectchPokemonInfo2(name: String) = liveData<PokemonInfoModel> {
        pokemonRepository.fetchPokemonInfo(name)
            .onStart {
                // 在调用 flow 请求数据之前，做一些准备工作，例如显示正在加载数据的按钮
                mLoading.set(true)
            }
            .catch {
                // 捕获上游出现的异常
                mLoading.set(false)
            }
            .onCompletion {
                // 请求完成
                mLoading.set(false)
            }
            .collectLatest { result ->
                result.doFailure { throwable ->
                    _failure.value = throwable?.message ?: "failure"
                }
                result.doSuccess { value ->
                    _pokemon.postValue(value)
                    emit(value)
                }
            }
    }

    /**
     * 方法三
     */
    suspend fun fetchPokemonInfo3(name: String) =
        pokemonRepository.fetchPokemonInfo(name)
            .onStart {
                // 在调用 flow 请求数据之前，做一些准备工作，例如显示正在加载数据的按钮
                mLoading.set(true)
            }
            .catch {
                // 捕获上游出现的异常
                mLoading.set(false)
            }
            .onCompletion {
                // 请求完成
                mLoading.set(false)
            }.asLiveData()

    /**
     * 方法一
     */
    fun fetchPokemonInfo1(name: String) = viewModelScope.launch {
        pokemonRepository.fetchPokemonInfo(name)
            .onStart {
                // 在调用 flow 请求数据之前，做一些准备工作，例如显示正在加载数据的按钮
                mLoading.set(true)
            }
            .catch {
                // 捕获上游出现的异常
                mLoading.set(false)
            }
            .onCompletion {
                // 请求完成
                mLoading.set(false)
            }
            .collectLatest { result ->
                result.doFailure { throwable ->
                    _failure.value = throwable?.message ?: "failure"
                }

                result.doSuccess { value ->
                    _pokemon.postValue(value)
                }
            }
    }


    companion object {
        private val TAG = "DetailViewModel"
    }

}