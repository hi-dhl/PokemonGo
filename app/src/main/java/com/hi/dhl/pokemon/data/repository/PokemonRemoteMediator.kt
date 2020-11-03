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

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.hi.dhl.pokemon.AppHelper
import com.hi.dhl.pokemon.data.entity.PokemonEntity
import com.hi.dhl.pokemon.data.entity.RemoteKeysEntity
import com.hi.dhl.pokemon.data.local.AppDataBase
import com.hi.dhl.pokemon.data.remote.PokemonService
import com.hi.dhl.pokemon.ext.isConnectedNetwork
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/7/11
 *     desc  :
 * </pre>
 */
@OptIn(ExperimentalPagingApi::class)
class PokemonRemoteMediator(
    val api: PokemonService,
    val db: AppDataBase
) : RemoteMediator<Int, PokemonEntity>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, PokemonEntity>
    ): MediatorResult {
        try {

            /**
             * 在这个方法内将会做三件事
             *
             * 1. 参数 LoadType 有个三个值，关于这三个值如何进行判断
             *      LoadType.REFRESH
             *      LoadType.PREPEND
             *      LoadType.APPEND
             *
             * 2. 访问网络数据
             *
             * 3. 将网路插入到本地数据库中
             */

            val pokemonDao = db.pokemonDao()
            val remoteKeysDao = db.remoteKeysDao()
            Timber.tag(TAG).e("loadType = ${loadType}")
            // 第一步： 判断 LoadType
            val pageKey = when (loadType) {
                // 首次访问 或者调用 PagingDataAdapter.refresh()
                LoadType.REFRESH -> null

                // 在当前加载的数据集的开头加载数据时
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)

                LoadType.APPEND -> { // 下来加载更多时触发

                    /**
                     *
                     * 这里主要获取下一页数据的开始位置，可以理解为从什么地方开始加载下一页数据
                     * 这里有两种方式来获取下一页加载数据的位置
                     * 方式一：这种方式比较简单，当前页面最后一条数据是下一页的开始位置
                     * 方式二：比较麻烦，当前分页数据没有对应的远程 key，这个时候需要我们自己建表,
                     */

                    /**
                     * 方式一：这种方式比较简单，当前页面最后一条数据是下一页的开始位置
                     * 通过 load 方法的参数 state 获取当页面最后一条数据
                     */
                    val lastItem = state.lastItemOrNull()
                    if (lastItem == null) {
                        return MediatorResult.Success(
                            endOfPaginationReached = true
                        )
                    }
                    lastItem.page

                    /**
                     * 方式二：比较麻烦，当前分页数据没有对应的远程 key，这个时候需要我们自己建表
                     */
//                    val remoteKey = db.withTransaction {
//                        db.remoteKeysDao().getRemoteKeys(remotePokemon)
//                    }
//                    if (remoteKey == null || remoteKey.nextKey == null) {
//                        return MediatorResult.Success(endOfPaginationReached = true)
//                    }
//                    remoteKey.nextKey
                }
            }

            if (!AppHelper.mContext.isConnectedNetwork()) {
                // 无网络加载本地数据
                return MediatorResult.Success(endOfPaginationReached = true)
            }

            // 第二步： 请问网络分页数据
            val page = pageKey ?: 0
            val result = api.fetchPokemonList(
                state.config.pageSize,
                page * state.config.pageSize
            ).results
            Timber.tag(TAG).e(result.toString())

            val endOfPaginationReached = result.isEmpty()

            val item = result.map {
                PokemonEntity(
                    name = it.name,
                    url = it.getImageUrl(),
                    remoteName = remotePokemon,
                    page = page + 1
                )
            }

            // 第三步： 插入数据库
            db.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    remoteKeysDao.clearRemoteKeys(remotePokemon)
                    pokemonDao.clearPokemon(remotePokemon)
                }
                val nextKey = if (endOfPaginationReached) null else page + 1
                val entity = RemoteKeysEntity(
                    remoteName = remotePokemon,
                    nextKey = nextKey
                )
                remoteKeysDao.insertAll(entity)
                pokemonDao.insertPokemon(item)
            }

            return MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (e: IOException) {
            return MediatorResult.Error(e)
        } catch (e: HttpException) {
            return MediatorResult.Error(e)
        }
    }

    companion object {
        private val TAG = "PokemonRemoteMediator"
        private val remotePokemon = "pokemon"
    }


}