package com.hi.dhl.pokemon.model

import androidx.recyclerview.widget.DiffUtil
import kotlin.random.Random

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/7/11
 *     desc  :
 * </pre>
 */
data class PokemonInfoModel(
    val name: String,
    val height: Int,
    val weight: Int,
    val experience: Int,
    val types: List<Type>,
    val stats: List<Stats>,
    val albums: List<AlbumModel>,
    val hp: Int = Random.nextInt(maxHp),
    val attack: Int = Random.nextInt(maxAttack),
    val speed: Int = Random.nextInt(maxSpeed),
    val exp: Int = Random.nextInt(maxExp)
) {

    fun generatetWeight(): String = "%.1f KG".format(weight.toFloat() / 10)
    fun generateHeight(): String = "%.1f M".format(height.toFloat() / 10)
    fun generatetHP(): String = "$hp/$maxHp HP"
    fun generatetAttack(): String = "$attack/$maxAttack"
    fun generatetSpeed(): String = "$speed/$maxSpeed"
    fun generatetExp(): String = "$exp/$maxExp"

    override fun toString(): String {
        return "PokemonInfoModel(name='$name', height=$height, weight=$weight, experience=$experience)"
    }

    data class AlbumModel(val index: Int, val url: String) {
        companion object {
            val diffCallback = object : DiffUtil.ItemCallback<AlbumModel>() {
                override fun areItemsTheSame(
                    oldItem: AlbumModel,
                    newItem: AlbumModel
                ): Boolean =
                    oldItem.index == newItem.index

                override fun areContentsTheSame(
                    oldItem: AlbumModel,
                    newItem: AlbumModel
                ): Boolean =
                    oldItem == newItem
            }
        }
    }

    data class Type(val name: String, val url: String)

    data class Stats(val baseStat: Int, val name: String, val url: String)

    companion object {
        const val maxHp = 500
        const val maxAttack = 600
        const val maxSpeed = 400
        const val maxExp = 800
    }
}