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

package com.hi.dhl.pokemon.ui.detail

import android.content.Context
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.hi.dhl.jdatabinding.DataBindingAppCompatActivity
import com.hi.dhl.pokemon.R
import com.hi.dhl.pokemon.databinding.ActivityDetailsBinding
import com.hi.dhl.pokemon.model.PokemonItemModel
import dagger.hilt.android.AndroidEntryPoint
import org.jetbrains.anko.startActivity

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/7/11
 *     desc  :
 * </pre>
 */

@AndroidEntryPoint
class DetailActivity : DataBindingAppCompatActivity() {

    private val mBindingActivity: ActivityDetailsBinding by binding(R.layout.activity_details)
    private val mViewModel: DetailViewModel by viewModels()
    lateinit var mPokemonModel: PokemonItemModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBindingActivity.apply {
            mPokemonModel = requireNotNull(intent.getParcelableExtra(KEY_LIST_MODEL))
            pokemonListModel = mPokemonModel
            lifecycleOwner = this@DetailActivity
            viewModel = mViewModel.apply {
                fectchPokemonInfo(mPokemonModel.name)
                    .observe(this@DetailActivity, Observer {})
            }
        }
    }

    companion object {
        private val TAG = "DetailActivity"
        private val KEY_LIST_MODEL = "listModel"
        fun jumpAcrtivity(act: Context, params: PokemonItemModel) {
            act.startActivity<DetailActivity>(KEY_LIST_MODEL to params)
        }
    }
}