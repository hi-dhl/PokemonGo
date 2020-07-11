package com.hi.dhl.pokemon.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/7/6
 *     desc  :
 * </pre>
 */

@Entity
data class PokemonInfoEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    val name: String,
    val height: Int,
    val weight: Int,
    val experience: Int
)

