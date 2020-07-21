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

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.paging.PagingDataAdapter
import com.hi.dhl.jdatabinding.DataBindingViewHolder
import com.hi.dhl.jdatabinding.dowithTry
import com.hi.dhl.pokemon.R
import com.hi.dhl.pokemon.databinding.RecycleItemPokemonBinding
import com.hi.dhl.pokemon.model.PokemonItemModel

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/7/11
 *     desc  :
 * </pre>
 */

class PokemonAdapter :
    PagingDataAdapter<PokemonItemModel, PokemonViewModel>(PokemonItemModel.diffCallback) {

    override fun onBindViewHolder(holder: PokemonViewModel, position: Int) {
        dowithTry {
            val data = getItem(position)
            data?.let {
                holder.bindData(data, position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewModel {
        val view = inflateView(parent, R.layout.recycle_item_pokemon)
        return PokemonViewModel(view)
    }

    private fun inflateView(viewGroup: ViewGroup, @LayoutRes viewType: Int): View {
        val layoutInflater = LayoutInflater.from(viewGroup.context)
        return layoutInflater.inflate(viewType, viewGroup, false)
    }
}

class PokemonViewModel(view: View) : DataBindingViewHolder<PokemonItemModel>(view) {
    private val mBinding: RecycleItemPokemonBinding by viewHolderBinding(view)

    override fun bindData(data: PokemonItemModel, position: Int) {
        mBinding.apply {
            data.id = "#${position + 1}"
            pokemon = data
            executePendingBindings()
        }
    }

}