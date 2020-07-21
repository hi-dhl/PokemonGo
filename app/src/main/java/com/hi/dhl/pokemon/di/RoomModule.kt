/*
 * Copyright 2020. hi-dhl (Jack Deng)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hi.dhl.pokemon.di

import android.app.Application
import androidx.room.Room
import com.hi.dhl.pokemon.data.local.AppDataBase
import com.hi.dhl.pokemon.data.local.PokemonDao
import com.hi.dhl.pokemon.data.local.PokemonInfoDao
import com.hi.dhl.pokemon.data.local.RemoteKeysDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/7/11
 *     desc  :
 * </pre>
 */
@Module
@InstallIn(ApplicationComponent::class)
// 这里使用了 ApplicationComponent，因此 NetworkModule 绑定到 Application 的生命周期。
object RoomModule {

    /**
     * @Provides 常用于被 @Module 注解标记类的内部的方法，并提供依赖项对象。
     * @Singleton 提供单例
     */
    @Provides
    @Singleton
    fun provideAppDataBase(application: Application): AppDataBase {
        return Room
            .databaseBuilder(application, AppDataBase::class.java, "dhl.db")
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }

    @Provides
    @Singleton
    fun providerPokemonDao(appDataBase: AppDataBase): PokemonDao {
        return appDataBase.pokemonDao()
    }

    @Provides
    @Singleton
    fun providerPokemonInfoDao(appDataBase: AppDataBase): PokemonInfoDao {
        return appDataBase.pokemonInfoDao()
    }

    @Provides
    @Singleton
    fun providerRemoteKeysDao(appDataBase: AppDataBase): RemoteKeysDao {
        return appDataBase.remoteKeysDao()
    }
}