package com.hi.dhl.pokemon.data.entity

import com.google.gson.annotations.SerializedName

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
    val types: List<TypeResponse>
) {

    data class TypeResponse(
        @SerializedName("slot") private val slot: Int,
        @SerializedName("type") private val type: Type
    )

    data class Type(
        @SerializedName("name") private val name: String
    )
}

