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

package com.hi.dhl.pokemon.data.repository

import androidx.paging.PagingData
import com.hi.dhl.pokemon.data.remote.PokemonResult
import com.hi.dhl.pokemon.model.PokemonInfoModel
import com.hi.dhl.pokemon.model.PokemonItemModel
import kotlinx.coroutines.flow.Flow

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/7/11
 *     desc  :
 * </pre>
 */
interface Repository {
    fun fetchPokemonList(): Flow<PagingData<PokemonItemModel>>

    suspend fun fetchPokemonInfo(name: String): Flow<PokemonResult<PokemonInfoModel>>

    suspend fun fetchPokemonByParameter(parameter: String): Flow<PagingData<PokemonItemModel>>
}