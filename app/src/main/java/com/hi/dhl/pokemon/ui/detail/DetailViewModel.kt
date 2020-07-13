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
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.hi.dhl.pokemon.data.repository.Repository
import com.hi.dhl.pokemon.model.PokemonInfoModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import timber.log.Timber

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/7/11
 *     desc  :
 * </pre>
 */
class DetailViewModel @ViewModelInject constructor(
    val polemonRepository: Repository
) : ViewModel() {
    val mLoading = ObservableBoolean()

    private val _pokemon = MutableLiveData<PokemonInfoModel>()
    val pokemon: LiveData<PokemonInfoModel> = _pokemon

    @OptIn(ExperimentalCoroutinesApi::class)
    fun fectchPokemonInfo(name: String) = liveData<PokemonInfoModel> {
        polemonRepository.featchPokemonInfo(name)
            .onStart {
                mLoading.set(true)
            }
            .catch {
                mLoading.set(false)
            }
            .onCompletion { mLoading.set(false) }
            .collectLatest {
                _pokemon.postValue(it)
                emit(it)
            }
    }

    companion object {
        private val TAG = "DetailViewModel"
    }

}