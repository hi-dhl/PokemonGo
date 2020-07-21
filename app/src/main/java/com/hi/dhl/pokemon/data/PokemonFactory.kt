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

package com.hi.dhl.pokemon.data

import androidx.paging.PagingConfig
import com.hi.dhl.pokemon.data.local.AppDataBase
import com.hi.dhl.pokemon.data.mapper.Entity2ItemModelMapper
import com.hi.dhl.pokemon.data.mapper.InfoEntity2InfoModelMapper
import com.hi.dhl.pokemon.data.remote.PokemonService
import com.hi.dhl.pokemon.data.repository.PokemonRepositoryImpl
import com.hi.dhl.pokemon.data.repository.Repository

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/7/11
 *     desc  :
 * </pre>
 */
object PokemonFactory {

    fun makePokemonRepository(api: PokemonService, db: AppDataBase): Repository =
        PokemonRepositoryImpl(
            api,
            db,
            pagingConfig,
            Entity2ItemModelMapper(),
            InfoEntity2InfoModelMapper()
        )

    val pagingConfig = PagingConfig(
        // 每页显示的数据的大小
        pageSize = 30,

        // 开启占位符
        enablePlaceholders = true,

        // 预刷新的距离，距离最后一个 item 多远时加载数据
        // 默认为 pageSize
        prefetchDistance = 4,

        /**
         * 初始化加载数量，默认为 pageSize * 3
         *
         * internal const val DEFAULT_INITIAL_PAGE_MULTIPLIER = 3
         * val initialLoadSize: Int = pageSize * DEFAULT_INITIAL_PAGE_MULTIPLIER
         */
        initialLoadSize = 30
    )
}