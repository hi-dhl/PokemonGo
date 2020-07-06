package com.hi.dhl.pokemon.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hi.dhl.pokemon.data.entity.PokemonEntity

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/7/6
 *     desc  :
 * </pre>
 */
@Dao
interface PokemonDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPokemonList(pokemonList: List<PokemonEntity>)

    @Query("SELECT * FROM PokemonEntity WHERE page = :page_")
    fun getPokemonList(page_: Int): List<PokemonEntity>
}