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

package com.hi.dhl.pokemon.ui.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.hi.dhl.pokemon.data.repository.Repository
import com.hi.dhl.pokemon.model.PokemonItemModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flatMapLatest

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/7/11
 *     desc  :
 * </pre>
 */

@FlowPreview
@ExperimentalCoroutinesApi
class MainViewModel @ViewModelInject constructor(
    private val pokemonRepository: Repository
) : ViewModel() {

    private val mChanncel = ConflatedBroadcastChannel<String>()

    private val _failure = MutableLiveData<String>()
    val failure = _failure

    // 通过 paging3 加载数据
    fun postOfData(): LiveData<PagingData<PokemonItemModel>> =
        pokemonRepository.fetchPokemonList().cachedIn(viewModelScope).asLiveData()

    // 使用 ConflatedBroadcastChannel 进行搜索
    val searchLiveData = mChanncel.asFlow()
        .flatMapLatest { search ->
            pokemonRepository.fetchPokemonByParameter(search).cachedIn(viewModelScope)
        }
        .catch { throwable ->
            _failure.value = throwable.message
        }.asLiveData()

    fun searchQueryParamter(paramter: String) = mChanncel.offer(paramter)

}