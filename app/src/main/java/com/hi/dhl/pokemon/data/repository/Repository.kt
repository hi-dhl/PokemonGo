package com.hi.dhl.pokemon.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagingData
import com.hi.dhl.pokemon.model.PokemonInfoModel
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
    fun featchPokemonList(): Flow<PagingData<PokemonListModel>>

    suspend fun featchPokemonInfo(name: String): Flow<PokemonInfoModel>
}