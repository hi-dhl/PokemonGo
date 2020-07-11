package com.hi.dhl.paging3.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hi.dhl.pokemon.data.entity.PokemonEntity
import com.hi.dhl.pokemon.data.entity.PokemonInfoEntity
import com.hi.dhl.pokemon.data.entity.RemoteKeysEntity
import com.hi.dhl.pokemon.data.local.PokemonDao
import com.hi.dhl.pokemon.data.local.PokemonInfoDao
import com.hi.dhl.pokemon.data.local.RemoteKeysDao

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/6/24
 *     desc  :
 * </pre>
 */

@Database(
    entities = arrayOf(PokemonEntity::class, RemoteKeysEntity::class, PokemonInfoEntity::class),
    version = 1, exportSchema = false
)
abstract class AppDataBase : RoomDatabase() {

    abstract fun pokemonDao(): PokemonDao
    abstract fun remoteKeysDao(): RemoteKeysDao
    abstract fun pokemonInfoDao(): PokemonInfoDao
}
