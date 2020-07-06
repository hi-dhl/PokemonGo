package com.hi.dhl.pokemon.data.mapper

import com.hi.dhl.pokemon.data.entity.ListingData
import com.hi.dhl.pokemon.model.PokemonListModel

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/7/6
 *     desc  :
 * </pre>
 */
class Response2ModelMapper : Mapper<ListingData, PokemonListModel> {

    override fun map(input: ListingData): PokemonListModel =
        PokemonListModel(input.page, input.name, input.getImageUrl())

}