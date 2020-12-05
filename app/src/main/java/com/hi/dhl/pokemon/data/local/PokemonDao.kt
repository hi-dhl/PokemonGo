package com.hi.dhl.pokemon.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hi.dhl.pokemon.data.entity.PokemonEntity

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/7/11
 *     desc  :
 * </pre>
 */
@Dao
interface PokemonDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPokemon(pokemonList: List<PokemonEntity>)

    @Query("SELECT * FROM PokemonEntity")
    fun getPokemon(): PagingSource<Int, PokemonEntity>

    @Query("DELETE FROM PokemonEntity where remoteName = :name")
    suspend fun clearPokemon(name: String)

    @Query("SELECT * FROM PokemonEntity where name LIKE '%' || :parameter || '%'")
    fun pokemonInfoByParameter(parameter: String): PagingSource<Int, PokemonEntity>
}