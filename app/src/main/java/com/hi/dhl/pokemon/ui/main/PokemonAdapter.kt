package com.hi.dhl.pokemon.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.hi.dhl.jdatabinding.DataBindingViewHolder
import com.hi.dhl.jdatabinding.dowithTry
import com.hi.dhl.pokemon.R
import com.hi.dhl.pokemon.databinding.RecycleItemPokemonBinding
import com.hi.dhl.pokemon.model.PokemonListModel
import com.hi.dhl.pokemon.ui.detail.DetailActivity

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/7/7
 *     desc  :
 * </pre>
 */

class PokemonAdapter :
    PagingDataAdapter<PokemonListModel, PokemonViewModel>(PokemonListModel.diffCallback) {

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

class PokemonViewModel(view: View) : DataBindingViewHolder<PokemonListModel>(view) {
    private val mBinding: RecycleItemPokemonBinding by viewHolderBinding(view)

    override fun bindData(data: PokemonListModel, position: Int) {
        mBinding.apply {
            pokemon = data
            executePendingBindings()
        }
    }

}