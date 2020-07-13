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

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.hi.dhl.paging3.data.local.AppDataBase
import com.hi.dhl.pokemon.data.entity.PokemonEntity
import com.hi.dhl.pokemon.data.entity.PokemonInfoEntity
import com.hi.dhl.pokemon.data.mapper.Mapper
import com.hi.dhl.pokemon.data.remote.PokemonService
import com.hi.dhl.pokemon.model.PokemonInfoModel
import com.hi.dhl.pokemon.model.PokemonItemModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import timber.log.Timber

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/7/11
 *     desc  :
 * </pre>
 */

class PokemonRepositoryImpl(
    val api: PokemonService,
    val db: AppDataBase,
    val pageConfig: PagingConfig,
    val mapper2ItemMolde: Mapper<PokemonEntity, PokemonItemModel>,
    val mapper2InfoModel: Mapper<PokemonInfoEntity, PokemonInfoModel>
) : Repository {

    override fun featchPokemonList(): Flow<PagingData<PokemonItemModel>> {
        return Pager(
            config = pageConfig,
            remoteMediator = PokemonRemoteMediator(api, db)
        ) {
            db.pokemonDao().getPokemon()
        }.flow.map { pagingData ->
            pagingData.map { mapper2ItemMolde.map(it) }
        }
    }

    override suspend fun featchPokemonInfo(name: String): Flow<PokemonInfoModel> {
        return flow {
            val pokemonDao = db.pokemonInfoDao()
            var infoModel = pokemonDao.getPokemon(name)
            // 查询数据库是否存在，如果不存在请求网络
            if (infoModel == null) {
                // 网络请求
                val netWorkPokemonInfo = api.fetchPokemonInfo(name)
                infoModel = netWorkPokemonInfo.let {
                    PokemonInfoEntity(
                        name = it.name,
                        height = it.height,
                        weight = it.weight,
                        experience = it.experience
                    )
                }
                pokemonDao.insertPokemon(infoModel) // 插入更新数据库
            }

            val model = mapper2InfoModel.map(infoModel)
            emit(model)
        }.flowOn(Dispatchers.IO)
    }
}