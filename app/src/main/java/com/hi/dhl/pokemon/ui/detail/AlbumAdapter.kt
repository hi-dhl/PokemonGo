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