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

import android.view.View
import com.hi.dhl.jdatabinding.DataBindingListAdapter
import com.hi.dhl.jdatabinding.DataBindingViewHolder
import com.hi.dhl.pokemon.R
import com.hi.dhl.pokemon.databinding.RecycleItemAlbumBinding
import com.hi.dhl.pokemon.model.PokemonInfoModel

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/7/22
 *     desc  :
 * </pre>
 */

class AlbumAdapter :
    DataBindingListAdapter<PokemonInfoModel.AlbumModel>(PokemonInfoModel.AlbumModel.diffCallback) {
    override fun layout(position: Int): Int = R.layout.recycle_item_album

    override fun viewHolder(
        layout: Int,
        view: View
    ): DataBindingViewHolder<PokemonInfoModel.AlbumModel> = AlbumViewHolder(view)
}

class AlbumViewHolder(view: View) : DataBindingViewHolder<PokemonInfoModel.AlbumModel>(view) {

    private val mBinding: RecycleItemAlbumBinding by viewHolderBinding(view)

    override fun bindData(data: PokemonInfoModel.AlbumModel, position: Int) {
        mBinding.apply {
            album = data
            executePendingBindings()
        }
    }

}