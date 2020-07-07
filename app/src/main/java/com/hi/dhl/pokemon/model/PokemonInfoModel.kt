package com.hi.dhl.pokemon.model

import com.google.gson.annotations.SerializedName
import com.hi.dhl.pokemon.data.entity.NetWorkPokemonInfo
import kotlin.random.Random

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/7/7
 *     desc  :
 * </pre>
 */
data class PokemonInfoModel(
    val name: String,
    val height: Int,
    val weight: Int,
    val experience: Int
){
    override fun toString(): String {
        return "PokemonInfoModel(name='$name', height=$height, weight=$weight, experience=$experience)"
    }
}