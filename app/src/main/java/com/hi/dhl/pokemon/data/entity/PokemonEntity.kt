package com.hi.dhl.pokemon.data.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/7/6
 *     desc  :
 * </pre>
 */

@Entity
data class PokemonEntity(
    var pokemonId: Int = 0,
    val page: Int = 0,
    @PrimaryKey
    val name: String,
    val url: String,
    val remoteName: String
)