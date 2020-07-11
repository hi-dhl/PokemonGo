package com.hi.dhl.pokemon.model

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
    val experience: Int,
    val hp: Int = Random.nextInt(maxHp),
    val attack: Int = Random.nextInt(maxAttack),
    val speed: Int = Random.nextInt(maxSpeed),
    val exp: Int = Random.nextInt(maxExp)
) {

    fun getWeightString(): String = String.format("%.1f KG", weight.toFloat() / 10)
    fun getHeightString(): String = String.format("%.1f M", height.toFloat() / 10)
    fun getHpString(): String = "$hp/$maxHp HP"
    fun getAttackString(): String = "$attack/$maxAttack"
    fun getSpeedString(): String = "$speed/$maxSpeed"
    fun getExpString(): String = "$exp/$maxExp"

    override fun toString(): String {
        return "PokemonInfoModel(name='$name', height=$height, weight=$weight, experience=$experience)"
    }

    companion object {
        const val maxHp = 300
        const val maxAttack = 300
        const val maxDefense = 300
        const val maxSpeed = 300
        const val maxExp = 1000
    }
}