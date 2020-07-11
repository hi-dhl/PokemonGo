package com.hi.dhl.pokemon.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.hi.dhl.jdatabinding.DataBindingAppCompatActivity
import com.hi.dhl.pokemon.R
import com.hi.dhl.pokemon.databinding.ActivityMainBinding
import com.hi.dhl.pokemon.ui.main.footer.FooterAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : DataBindingAppCompatActivity() {
    private val mBidning: ActivityMainBinding by binding(R.layout.activity_main)
    private val mViewModel: MainViewModel by viewModels()
    private val mPomemonAdapter by lazy { PokemonAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBidning.apply {
            recyleView.adapter = mPomemonAdapter.withLoadStateFooter(FooterAdapter(mPomemonAdapter))
            mainViewModel = mViewModel
            lifecycleOwner = this@MainActivity
        }

        mViewModel.postOfData().observe(this, Observer {
            mPomemonAdapter.submitData(lifecycle, it)
            swiperRefresh.isRefreshing = false
        })

//        lifecycleScope.launchWhenCreated {
//            mPomemonAdapter.loadStateFlow.collectLatest { state ->
//                swiperRefresh.isRefreshing = state.refresh is LoadState.Loading
//            }
//        }

    }
}