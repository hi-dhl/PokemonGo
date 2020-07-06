package com.hi.dhl.pokemon.model

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
)