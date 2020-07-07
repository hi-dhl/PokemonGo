package com.hi.dhl.pokemon.model

import androidx.recyclerview.widget.DiffUtil

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/7/6
 *     desc  :
 * </pre>
 */
data class PokemonListModel(
    var page: Int = 0,
    val name: String,
    val url: String
) {
    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<PokemonListModel>() {
            override fun areItemsTheSame(
                oldItem: PokemonListModel,
                newItem: PokemonListModel
            ): Boolean =
                oldItem.page == newItem.page

            override fun areContentsTheSame(
                oldItem: PokemonListModel,
                newItem: PokemonListModel
            ): Boolean =
                oldItem == newItem
        }
    }
}