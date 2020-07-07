package com.hi.dhl.pokemon.model

import android.os.Parcelable
import androidx.recyclerview.widget.DiffUtil
import kotlinx.android.parcel.Parcelize

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/7/6
 *     desc  :
 * </pre>
 */

@Parcelize
data class PokemonListModel(
    var page: Int = 0,
    val name: String,
    val url: String
) : Parcelable {
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