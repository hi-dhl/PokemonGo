package com.hi.dhl.pokemon.data.mapper

import com.hi.dhl.pokemon.data.entity.ListingData
import com.hi.dhl.pokemon.data.entity.NetWorkPokemonInfo
import com.hi.dhl.pokemon.data.entity.PokemonInfoEntity
import com.hi.dhl.pokemon.model.PokemonInfoModel
import com.hi.dhl.pokemon.model.PokemonListModel
import timber.log.Timber
import kotlin.random.Random

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/7/6
 *     desc  :
 * </pre>
 */
class Response2InfoModelMapper : Mapper<PokemonInfoEntity, PokemonInfoModel> {

    override fun map(input: PokemonInfoEntity): PokemonInfoModel {

        return PokemonInfoModel(
            input.name,
            input.height,
            input.weight,
            input.experience
        )
    }


}