package com.hi.dhl.pokemon.data.mapper

import com.hi.dhl.pokemon.data.entity.ListingData
import com.hi.dhl.pokemon.data.entity.PokemonEntity
import com.hi.dhl.pokemon.model.PokemonListModel

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/7/6
 *     desc  :
 * </pre>
 */
class Response2ModelMapper : Mapper<PokemonEntity, PokemonListModel> {

    override fun map(input: PokemonEntity): PokemonListModel =
        PokemonListModel(name = input.name, url = input.url)

}