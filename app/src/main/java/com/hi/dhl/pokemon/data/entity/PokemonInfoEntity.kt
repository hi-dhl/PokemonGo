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

package com.hi.dhl.pokemon.data.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.hi.dhl.pokemon.ext.getEmptyOrDefault

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/7/11
 *     desc  :
 * </pre>
 */

@Entity
data class PokemonInfoEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    val name: String,
    val height: Int,
    val weight: Int,
    val experience: Int,
    val types: List<Type>,
    val stats: List<Stats>,
    @Embedded val sprites: Sprites
) {

    data class Sprites(
        val backDefault: String,
        val backFemale: String,
        val backShiny: String,
        val backShinyFemale: String,
        val frontDefault: String,
        val frontfemale: String,
        val frontShiny: String,
        val frontShinyFemale: String
    )

    data class Type(val name: String, val url: String)

    data class Stats(val baseStat: Int, val name: String, val url: String)

    companion object {
        fun convert2PokemonInfoEntity(netWorkPokemonInfo: NetWorkPokemonInfo): PokemonInfoEntity {
            return netWorkPokemonInfo.run {

                val dbTypes = mutableListOf<Type>()
                val dbStats = mutableListOf<Stats>()

                types.forEach {
                    dbTypes.add(
                        Type(
                            name = it.type.name,
                            url = it.type.url
                        )
                    )
                }

                stats.forEach {
                    dbStats.add(
                        Stats(
                            baseStat = it.baseStat,
                            name = it.stat.name,
                            url = it.stat.url
                        )
                    )
                }

                val dbSprites = Sprites(
                    backDefault = sprites.backDefault.getEmptyOrDefault { "" },
                    backFemale = sprites.backFemale.getEmptyOrDefault { "" },
                    backShiny = sprites.backShiny.getEmptyOrDefault { "" },
                    backShinyFemale = sprites.backShinyFemale.getEmptyOrDefault { "" },
                    frontDefault = sprites.frontDefault.getEmptyOrDefault { "" },
                    frontfemale = sprites.frontfemale.getEmptyOrDefault { "" },
                    frontShiny = sprites.frontShiny.getEmptyOrDefault { "" },
                    frontShinyFemale = sprites.frontShinyFemale.getEmptyOrDefault { "" }
                )

                PokemonInfoEntity(
                    name = name,
                    height = height,
                    weight = weight,
                    experience = experience,
                    types = dbTypes,
                    stats = dbStats,
                    sprites = dbSprites
                )
            }
        }
    }
}

