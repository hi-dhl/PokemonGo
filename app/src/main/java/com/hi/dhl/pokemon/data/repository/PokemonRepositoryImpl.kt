package com.hi.dhl.pokemon.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.hi.dhl.pokemon.data.entity.ListingData
import com.hi.dhl.pokemon.data.entity.NetWorkPokemonInfo
import com.hi.dhl.pokemon.data.mapper.Mapper
import com.hi.dhl.pokemon.data.remote.PokemonService
import com.hi.dhl.pokemon.model.PokemonInfoModel
import com.hi.dhl.pokemon.model.PokemonListModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import timber.log.Timber

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
    val mapper2Molde: Mapper<ListingData, PokemonListModel>,
    val mapper2InfoModel: Mapper<NetWorkPokemonInfo, PokemonInfoModel>
) : Repository {

    override fun featchPokemonList(): Flow<PagingData<PokemonListModel>> {
        return Pager(pageConfig) {
            // 加载数据库的数据
            PokemonItemPagingSource(api)
        }.flow.map { listerData ->
            listerData.map { mapper2Molde.map(it) }
        }
    }

    override suspend fun featchPokemonInfo(name: String): Flow<PokemonInfoModel> {
        return flow {
            val json = api.fetchPokemonInfo()
            Timber.tag("featchPokemonInfo").e(json.toString())
            val model = mapper2InfoModel.map(json)
            emit(model)
        }.flowOn(Dispatchers.IO)
    }
}