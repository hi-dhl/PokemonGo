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

package com.hi.dhl.pokemon.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hi.dhl.pokemon.data.entity.PokemonInfoEntity

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/7/11
 *     desc  :
 * </pre>
 */

@Dao
interface PokemonInfoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPokemon(pokemonInfoEntity: PokemonInfoEntity)

    @Query("SELECT * FROM PokemonInfoEntity where name = :name")
    suspend fun getPokemon(name: String): PokemonInfoEntity?

    @Query("DELETE FROM PokemonInfoEntity where id = :id")
    suspend fun clearPokemonInfo(id: Int)
}