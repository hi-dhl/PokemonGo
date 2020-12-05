package com.hi.dhl.pokemon.ui.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.hi.dhl.pokemon.data.repository.Repository
import com.hi.dhl.pokemon.model.PokemonItemModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.*

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/7/11
 *     desc  :
 * </pre>
 */

@FlowPreview
@ExperimentalCoroutinesApi
class MainViewModel @ViewModelInject constructor(
    private val pokemonRepository: Repository
) : ViewModel() {

    private val mChanncel = ConflatedBroadcastChannel<String>()

    // 使用 StateFlow 替换 ConflatedBroadcastChannel
    private val _stateFlow = MutableStateFlow<String>("") // 在 MainViewModel 内部使用
    val stateFlow = _stateFlow // 在外部使用

    // 通过 paging3 加载数据
    fun postOfData(): LiveData<PagingData<PokemonItemModel>> =
        pokemonRepository.fetchPokemonList().cachedIn(viewModelScope).asLiveData()

    // 使用 ConflatedBroadcastChannel 进行搜索
    val searchResultForDb = mChanncel.asFlow()
        // 避免在单位时间内，快输入造成大量的请求
        .debounce(200)
        //  避免重复的搜索请求。假设正在搜索 dhl，用户删除了 l  然后输入 l。最后的结果还是 dhl。它就不会再执行搜索查询 dhl
        // distinctUntilChanged 对于 StateFlow 任何实例是没有效果的
        .distinctUntilChanged()
        .flatMapLatest { search -> // 只显示最后一次搜索的结果，忽略之前的请求
            pokemonRepository.fetchPokemonByParameter(search).cachedIn(viewModelScope)
        }
        .catch { throwable ->
            //  异常捕获
        }.asLiveData()

    /**
     * 使用 MutableStateFlow 进行网络搜索
     *
     * 因为没有合适的搜索接口，在这里模拟进行网络搜索
     */
    val searchResultMockNetWork =
        // 避免在单位时间内，快输入造成大量的请求
        stateFlow.debounce(200)
            .filter { result ->
                if (result.isEmpty()) { // 过滤掉空字符串等等无效输入
                    return@filter false
                } else {
                    return@filter true
                }
            }
            .flatMapLatest { // 只显示最后一次搜索的结果，忽略之前的请求
                // 网络请求，这里替换自己的实现即可
                pokemonRepository.fetchPokemonList().cachedIn(viewModelScope)
            }
            .catch { throwable ->
                //  异常捕获
            }
            .asLiveData()

    // 根据关键词搜索
    fun queryParameterForDb(parameter: String) = mChanncel.offer(parameter)

    // 根据关键词搜索
    fun queryParameterForNetWork(parameter: String) {
        _stateFlow.value = parameter
    }

}