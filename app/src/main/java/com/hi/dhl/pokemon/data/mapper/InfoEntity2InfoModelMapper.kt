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

package com.hi.dhl.pokemon.data.mapper

import com.hi.dhl.pokemon.data.entity.PokemonInfoEntity
import com.hi.dhl.pokemon.model.PokemonInfoModel

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/7/11
 *     desc  :
 * </pre>
 */
class InfoEntity2InfoModelMapper : Mapper<PokemonInfoEntity, PokemonInfoModel> {

    override fun map(input: PokemonInfoEntity): PokemonInfoModel {

        return convert2PokemonInfoEntity(input)
    }

    fun convert2PokemonInfoEntity(pokemonInfoModel: PokemonInfoEntity): PokemonInfoModel {
        return pokemonInfoModel.run {

            val dbTypes = mutableListOf<PokemonInfoModel.Type>()
            val dbStats = mutableListOf<PokemonInfoModel.Stats>()

            types.forEach {
                dbTypes.add(
                    PokemonInfoModel.Type(
                        name = it.name,
                        url = it.url
                    )
                )
            }

            stats.forEach {
                dbStats.add(
                    PokemonInfoModel.Stats(
                        baseStat = it.baseStat,
                        name = it.name,
                        url = it.url
                    )
                )
            }

            val albumsItem = mutableListOf<PokemonInfoModel.AlbumModel>()
            if (!sprites.backDefault.isEmpty()) {
                albumsItem.add(PokemonInfoModel.AlbumModel(index = 1, url = sprites.backDefault))
            }
            if (!sprites.backShiny.isEmpty()) {
                albumsItem.add(PokemonInfoModel.AlbumModel(index = 3, url = sprites.backShiny))
            }
            if (!sprites.frontDefault.isEmpty()) {
                albumsItem.add(PokemonInfoModel.AlbumModel(index = 5, url = sprites.frontDefault))
            }
            if (!sprites.frontShiny.isEmpty()) {
                albumsItem.add(PokemonInfoModel.AlbumModel(index = 7, url = sprites.frontShiny))
            }

            PokemonInfoModel(
                name = name,
                height = height,
                weight = weight,
                experience = experience,
                types = dbTypes,
                stats = dbStats,
                albums = albumsItem
            )
        }
    }

}