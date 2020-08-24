/*
 * Copyright 2020. hi-dhl (Jack Deng)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hi.dhl.pokemon.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.hi.dhl.jdatabinding.DataBindingAppCompatActivity
import com.hi.dhl.pokemon.R
import com.hi.dhl.pokemon.databinding.ActivityMainBinding
import com.hi.dhl.pokemon.ui.main.footer.FooterAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : DataBindingAppCompatActivity() {
    private val mBinding: ActivityMainBinding by binding(R.layout.activity_main)
    private val mViewModel: MainViewModel by viewModels()
    private val mPokemonAdapter by lazy { PokemonAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding.apply {
            recyleView.adapter = mPokemonAdapter.withLoadStateFooter(FooterAdapter(mPokemonAdapter))
            mainViewModel = mViewModel
            lifecycleOwner = this@MainActivity
        }

        mViewModel.postOfData().observe(this, Observer {
            mPokemonAdapter.submitData(lifecycle, it)
            swiperRefresh.isEnabled = false
        })
        
        lifecycleScope.launchWhenCreated {
            mPokemonAdapter.loadStateFlow.collectLatest { state ->
                swiperRefresh.isRefreshing = state.refresh is LoadState.Loading
            }
        }

    }
}