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
@Parcelize
data class PokemonEntity(
    @PrimaryKey
    var page: Int = 0,
    val name: String,
    val url: String
) : Parcelable