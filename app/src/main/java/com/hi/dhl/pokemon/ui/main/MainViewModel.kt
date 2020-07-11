package com.hi.dhl.pokemon.ui.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import androidx.paging.PagingData
import com.hi.dhl.pokemon.data.repository.Repository
import com.hi.dhl.pokemon.model.PokemonInfoModel
import com.hi.dhl.pokemon.model.PokemonListModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import timber.log.Timber

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/7/4
 *     desc  :
 * </pre>
 */

class MainViewModel @ViewModelInject constructor(
    val polemonRepository: Repository
) : ViewModel() {

    fun postOfData(): LiveData<PagingData<PokemonListModel>> =
        polemonRepository.featchPokemonList().asLiveData()

}