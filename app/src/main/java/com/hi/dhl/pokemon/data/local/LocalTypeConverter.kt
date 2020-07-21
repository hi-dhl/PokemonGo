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

import androidx.room.TypeConverter
import com.google.gson.GsonBuilder
import com.hi.dhl.pokemon.data.entity.PokemonInfoEntity
import com.hi.dhl.pokemon.ext.fromJson
import com.hi.dhl.pokemon.ext.typedToJson

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/7/21
 *     desc  :
 * </pre>
 */
open class LocalTypeConverter {

    @TypeConverter
    fun json2StatsEntity(src: String): List<PokemonInfoEntity.Stats>? =
        GsonBuilder().create().fromJson(src)

    @TypeConverter
    fun statsEntity2Json(data: List<PokemonInfoEntity.Stats>): String =
        GsonBuilder().create().typedToJson(data)

    @TypeConverter
    fun json2TypeEntity(src: String): List<PokemonInfoEntity.Type>? =
        GsonBuilder().create().fromJson(src)

    @TypeConverter
    fun typeEntity2Json(data: List<PokemonInfoEntity.Type>): String =
        GsonBuilder().create().typedToJson(data)

}