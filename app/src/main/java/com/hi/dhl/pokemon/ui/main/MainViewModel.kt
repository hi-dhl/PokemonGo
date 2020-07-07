package com.hi.dhl.pokemon.ui.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.paging.PagingData
import com.hi.dhl.pokemon.data.repository.Repository
import com.hi.dhl.pokemon.model.PokemonListModel
import kotlinx.coroutines.ExperimentalCoroutinesApi

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/7/4
 *     desc  :
 * </pre>
 */

class MainViewModel @ViewModelInject constructor(
    val mPolemonRepository: Repository
) : ViewModel() {

    fun postOfData(): LiveData<PagingData<PokemonListModel>> =
        mPolemonRepository.postOfData().asLiveData()
}