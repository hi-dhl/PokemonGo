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
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/7/6
 *     desc  :
 * </pre>
 */
@OptIn(ExperimentalPagingApi::class)
class PokemonRemoteMediator(
    val api: PokemonService,
    val db: AppDataBase
) : RemoteMediator<Int, PokemonEntity>() {
    val mPageKey = 0
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, PokemonEntity>
    ): MediatorResult {
        try {

            val pokemonDao = db.pokemonDao()
            val remoteKeysDao = db.remoteKeysDao()
            Timber.tag(TAG).e("loadType = ${loadType}")
            val pageKey = when (loadType) {
                // 首次访问 或者调用 PagingDataAdapter.refresh()
                LoadType.REFRESH -> null
                // 在当前加载的数据集的开头加载数据时
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                // 在当前数据集末尾添加数据
                LoadType.APPEND -> {
                    val remoteKey = db.withTransaction {
                        db.remoteKeysDao().getRemoteKeys(remotePokemon)
                    }
                    if (remoteKey == null || remoteKey.nextKey == null) {
                        return MediatorResult.Success(endOfPaginationReached = true)
                    }
                    remoteKey.nextKey
                }
            }

            if (!AppHelper.mContext.isConnectedNetwork()) {
                // 无网络加载本地数据
                return MediatorResult.Success(endOfPaginationReached = true)
            }

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
                    remoteName = remotePokemon
                )
            }

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
        private val TAG = "PokemonItemPagingSource"
        private val remotePokemon = "pokemon"
    }


}