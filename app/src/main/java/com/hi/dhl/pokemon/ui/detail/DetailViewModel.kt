package com.hi.dhl.pokemon.ui.detail

import androidx.databinding.ObservableBoolean
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.hi.dhl.pokemon.data.repository.Repository
import com.hi.dhl.pokemon.model.PokemonInfoModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import timber.log.Timber

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
    val mLoading = ObservableBoolean()
    private val pokemon = MutableLiveData<PokemonInfoModel>()
    val _pokemon: LiveData<PokemonInfoModel> = pokemon

    @OptIn(ExperimentalCoroutinesApi::class)
    fun fectchPokemonInfo(name: String) = liveData<PokemonInfoModel> {
        polemonRepository.featchPokemonInfo(name)
            .onStart {
                mLoading.set(true)
            }
            .catch {
                Timber.tag(TAG).e(it)
                mLoading.set(false)
            }
            .onCompletion { mLoading.set(false) }
            .collectLatest {
                pokemon.postValue(it)
                emit(it)
            }
    }

    companion object {
        private val TAG = "DetailViewModel"
    }

}