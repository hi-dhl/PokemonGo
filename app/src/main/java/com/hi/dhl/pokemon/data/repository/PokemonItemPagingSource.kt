package com.hi.dhl.pokemon.data.repository

import androidx.paging.PagingSource
import com.hi.dhl.pokemon.data.entity.ListingData
import com.hi.dhl.pokemon.data.remote.PokemonService
import timber.log.Timber

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/7/6
 *     desc  :
 * </pre>
 */
class PokemonItemPagingSource(
    val api: PokemonService
) : PagingSource<Int, ListingData>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ListingData> {
        return try {
            Timber.tag(TAG).e("key = ${params.key}")
            val key = params.key ?: 0
            val result = api.fetchPokemonList(
                params.loadSize,
                key * params.loadSize
            )
            Timber.tag(TAG).e(result.results.toString())

            LoadResult.Page(
                data = result.results, // 返回获取到的数据
                prevKey = null, // 上一页，设置为空就没有上一页的效果，这需要注意的是，如果是第一页需要返回 null，否则会出现多次请求
                nextKey = result.results.lastOrNull()
                    ?.let { key + 1 } // 下一页，设置为空就没有加载更多效果，需要注意的是，如果是最后一页返回 null
            )
        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(e)
        }
    }

    companion object {
        private val TAG = "PokemonItemPagingSource"
    }
}