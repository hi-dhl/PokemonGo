package com.hi.dhl.pokemon.data

import androidx.paging.PagingConfig
import com.hi.dhl.paging3.data.local.AppDataBase
import com.hi.dhl.pokemon.data.local.PokemonDao
import com.hi.dhl.pokemon.data.mapper.Response2InfoModelMapper
import com.hi.dhl.pokemon.data.mapper.Response2ModelMapper
import com.hi.dhl.pokemon.data.remote.PokemonService
import com.hi.dhl.pokemon.data.repository.PokemonRepositoryImpl
import com.hi.dhl.pokemon.data.repository.Repository

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/7/6
 *     desc  :
 * </pre>
 */
object PokemonFactory {

    fun makePokemonRepository(api: PokemonService, db: AppDataBase): Repository =
        PokemonRepositoryImpl(api, db, pagingConfig, Response2ModelMapper(), Response2InfoModelMapper())

    val pagingConfig = PagingConfig(
        // 每页显示的数据的大小
        pageSize = 30,
        // 开启占位符
        enablePlaceholders = false,

        // 预刷新的距离，距离最后一个 item 多远时加载数据
//        prefetchDistance = 3,

        /**
         * 初始化加载数量，默认为 pageSize * 3
         *
         * internal const val DEFAULT_INITIAL_PAGE_MULTIPLIER = 3
         * val initialLoadSize: Int = pageSize * DEFAULT_INITIAL_PAGE_MULTIPLIER
         */

        /**
         * 初始化加载数量，默认为 pageSize * 3
         *
         * internal const val DEFAULT_INITIAL_PAGE_MULTIPLIER = 3
         * val initialLoadSize: Int = pageSize * DEFAULT_INITIAL_PAGE_MULTIPLIER
         */

        /**
         * 初始化加载数量，默认为 pageSize * 3
         *
         * internal const val DEFAULT_INITIAL_PAGE_MULTIPLIER = 3
         * val initialLoadSize: Int = pageSize * DEFAULT_INITIAL_PAGE_MULTIPLIER
         */

        /**
         * 初始化加载数量，默认为 pageSize * 3
         *
         * internal const val DEFAULT_INITIAL_PAGE_MULTIPLIER = 3
         * val initialLoadSize: Int = pageSize * DEFAULT_INITIAL_PAGE_MULTIPLIER
         */
        initialLoadSize = 60
    )
}