package com.hi.dhl.pokemon.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.hi.dhl.pokemon.data.entity.ListingData
import com.hi.dhl.pokemon.data.mapper.Mapper
import com.hi.dhl.pokemon.data.remote.PokemonService
import com.hi.dhl.pokemon.model.PokemonListModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/7/6
 *     desc  :
 * </pre>
 */

class PokemonRepositoryImpl(
    val api: PokemonService,
    val pageConfig: PagingConfig,
    val mapper2Molde: Mapper<ListingData, PokemonListModel>
) : Repository {

    override fun postOfData(): Flow<PagingData<PokemonListModel>> {
        return Pager(pageConfig) {
            // 加载数据库的数据
            PokemonItemPagingSource(api)
        }.flow.map { listerData ->
            listerData.map { mapper2Molde.map(it) }
        }
    }
}