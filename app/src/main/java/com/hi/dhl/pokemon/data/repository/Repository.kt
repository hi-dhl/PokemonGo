package com.hi.dhl.pokemon.data.repository

import androidx.paging.PagingData
import com.hi.dhl.pokemon.model.PokemonListModel
import kotlinx.coroutines.flow.Flow

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/7/6
 *     desc  :
 * </pre>
 */
interface Repository {
    fun postOfData(): Flow<PagingData<PokemonListModel>>
}