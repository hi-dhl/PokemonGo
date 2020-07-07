package com.hi.dhl.pokemon.ui.detail

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.hi.dhl.pokemon.data.repository.Repository
import kotlinx.coroutines.launch

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/7/7
 *     desc  :
 * </pre>
 */
class DetailViewModel @ViewModelInject constructor(
    val polemonRepository: Repository
) : ViewModel() {

    fun fectchPokemonInfo(name: String) = liveData {
        viewModelScope.launch {
            emitSource(polemonRepository.featchPokemonInfo(name))
        }
    }

}