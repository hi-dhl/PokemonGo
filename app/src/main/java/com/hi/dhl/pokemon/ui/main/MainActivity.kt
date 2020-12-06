package com.hi.dhl.pokemon.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.hi.dhl.jdatabinding.binding
import com.hi.dhl.pokemon.R
import com.hi.dhl.pokemon.databinding.ActivityMainBinding
import com.hi.dhl.pokemon.ui.main.footer.FooterAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.collectLatest

@FlowPreview
@ExperimentalCoroutinesApi
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val mBinding: ActivityMainBinding by binding()
    private val mViewModel: MainViewModel by viewModels()
    private val mPokemonAdapter by lazy { PokemonAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding.apply {
            recyleView.adapter = mPokemonAdapter.withLoadStateFooter(FooterAdapter(mPokemonAdapter))
            mainViewModel = mViewModel
            lifecycleOwner = this@MainActivity
        }

        /**
         * 分为 数据库 和 网络搜索
         * 可以运行注释掉的代码，文章链接：https://juejin.cn/post/6854573220457086990
         */
        mBinding.layoutHeader.searchView.addTextChangedListener {
            val result = it.toString()
            mViewModel.queryParameterForDb(result) // 搜索数据库
//                mViewModel.queryParameterForNetWork(result) // 网络搜索
        }

        mViewModel.postOfData().observe(this, Observer {
            mPokemonAdapter.submitData(lifecycle, it)
            mBinding.swiperRefresh.isEnabled = false
        })

        lifecycleScope.launchWhenCreated {
            mPokemonAdapter.loadStateFlow.collectLatest { state ->
                mBinding.swiperRefresh.isRefreshing = state.refresh is LoadState.Loading
            }
        }

        // 数据库搜索回调监听
        mViewModel.searchResultForDb.observe(this, Observer {
            mPokemonAdapter.submitData(lifecycle, it)
        })

        // 网络搜索回调监听
        mViewModel.searchResultMockNetWork.observe(this, Observer {
//            mPokemonAdapter.submitData(lifecycle, it)
        })
    }

    companion object {
        private const val TAG = "MainActivity"
    }

    /**
     *  callbackFlow 提供了一个简单的回调 Api，并且在关闭的时候，移除注册监听
     *  在很多场景都可以使用，例如定位 locatoinManager#requestSingleUpdate 在 awaitClose 移除掉监听
     */
//    fun AppCompatEditText.addTextChangedListenerFlow(): Flow<String> = callbackFlow {
//        val watch = addTextChangedListener {
//            sendBlocking(it.toString().trim())
//        }
//        addTextChangedListener(watch)
////        awaitClose { removeTextChangedListener(watch) }
//    }
}