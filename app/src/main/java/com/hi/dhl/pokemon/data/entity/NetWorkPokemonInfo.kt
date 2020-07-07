package com.hi.dhl.pokemon.data.entity

import com.google.gson.annotations.SerializedName
import kotlin.random.Random

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/7/6
 *     desc  :
 * </pre>
 */

data class NetWorkPokemonInfo(
    @SerializedName("name") val name: String,
    @SerializedName("height") val height: Int,
    @SerializedName("weight") val weight: Int,
    @SerializedName("base_experience") val experience: Int,
    val types: List<TypeResponse>,
    val hp: Int = Random.nextInt(maxHp),
    val attack: Int = Random.nextInt(maxAttack),
    val defense: Int = Random.nextInt(maxDefense),
    val speed: Int = Random.nextInt(maxSpeed),
    val exp: Int = Random.nextInt(maxExp)

) {

    data class TypeResponse(
        @SerializedName("slot") private val slot: Int,
        @SerializedName("type") private val type: Type
    )

    data class Type(
        @SerializedName("name") private val name: String
    )

    companion object {
        private const val maxHp = 300
        private const val maxAttack = 300
        private const val maxDefense = 300
        private const val maxSpeed = 300
        private const val maxExp = 1000
    }
}

