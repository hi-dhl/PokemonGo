package com.hi.dhl.paging3.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hi.dhl.pokemon.data.entity.PokemonEntity
import com.hi.dhl.pokemon.data.local.PokemonDao

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/6/24
 *     desc  :
 * </pre>
 */

@Database(
    entities = arrayOf(PokemonEntity::class),
    version = 1, exportSchema = false
)
abstract class AppDataBase : RoomDatabase() {

    abstract fun pokemonDao(): PokemonDao
}
