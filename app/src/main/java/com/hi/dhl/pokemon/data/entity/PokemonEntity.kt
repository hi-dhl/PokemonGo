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
    @PrimaryKey(autoGenerate = true)
    var pokemonId: Int = 0,
    val name: String,
    val url: String
)