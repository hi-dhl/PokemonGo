package com.hi.dhl.pokemon.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.hi.dhl.paging3.data.local.AppDataBase
import com.hi.dhl.pokemon.AppHelper
import com.hi.dhl.pokemon.data.entity.PokemonEntity
import com.hi.dhl.pokemon.data.entity.RemoteKeysEntity
import com.hi.dhl.pokemon.data.remote.PokemonService
import com.hi.dhl.pokemon.ext.isConnectedNetwork
import timber.log.Timber

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/7/6
 *     desc  :
 * </pre>
 */
@OptIn(ExperimentalPagingApi::class)
class PokemonRemoteSource(
    val api: PokemonService,
    val db: AppDataBase
) : RemoteMediator<Int, PokemonEntity>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, PokemonEntity>
    ): MediatorResult {
        return try {

            val pokemonDao = db.pokemonDao()
            val remoteKeysDao = db.remoteKeysDao()

            val pageKey = when (loadType) {
                LoadType.REFRESH -> null
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    val remoteKey = db.withTransaction {
                        remoteKeysDao.getRemoteKeys(remotePokemon)
                    }

                    if (remoteKey.nextKey == null) {
                        return MediatorResult.Success(endOfPaginationReached = true)
                    }

                    remoteKey.nextKey
                }
            }

            if (!AppHelper.mContext.isConnectedNetwork()) {
                return MediatorResult.Success(endOfPaginationReached = true)
            }
            val key = pageKey ?: 0

            val result = api.fetchPokemonList(
                state.config.pageSize,
                when (loadType) {
                    LoadType.REFRESH -> key * state.config.initialLoadSize
                    else -> key * state.config.pageSize
                }
            )
            Timber.tag(TAG).e(result.results.toString())

            val endOfPaginationReached = result.results.isEmpty()

            db.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    remoteKeysDao.clearRemoteKeys()
                    pokemonDao.clearPokemon()
                }

                val nextKey = if (endOfPaginationReached) null else key + 1
                remoteKeysDao.insertAll(
                    RemoteKeysEntity(
                        remoteName = remotePokemon,
                        nextKey = nextKey
                    )
                )
                val item = result.results.map {
                    PokemonEntity(name = it.name, url = it.getImageUrl())
                }
                pokemonDao.insertPokemon(item)
            }

            MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (e: Exception) {
            Timber.tag(TAG).e(e)
            MediatorResult.Error(e)
        }
    }

//    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ListingData> {
//        return try {
//            val pokemonDao = db.pokemonDao()
//            Timber.tag(TAG).e("key = ${params.key}")
//            val key = params.key ?: 0
//            val result = api.fetchPokemonList(
//                params.loadSize,
//                key * params.loadSize
//            )
//            Timber.tag(TAG).e(result.results.toString())
//
//            LoadResult.Page(
//                data = result.results, // 返回获取到的数据
//                prevKey = null, // 上一页，设置为空就没有上一页的效果，这需要注意的是，如果是第一页需要返回 null，否则会出现多次请求
//                nextKey = result.results.lastOrNull()
//                    ?.let { key + 1 } // 下一页，设置为空就没有加载更多效果，需要注意的是，如果是最后一页返回 null
//            )
//        } catch (e: Exception) {
//            e.printStackTrace()
//            LoadResult.Error(e)
//        }
//    }

    companion object {
        private val TAG = "PokemonItemPagingSource"
        private val remotePokemon = "pokemon"
    }


}