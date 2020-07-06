package com.hi.dhl.pokemon.data.remote

import com.hi.dhl.pokemon.data.entity.ListingResponse
import com.hi.dhl.pokemon.data.entity.NetWorkPokemon
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/7/6
 *     desc  :
 * </pre>
 */
interface PokemonService {
    @GET("pokemon")
    fun fetchPokemonList(
        @Query("limit") limit: Int = 20,
        @Query("offset") offset: Int = 0
    ): ListingResponse

    @GET("pokemon/{name}")
    fun fetchPokemonInfo(@Path("name") name: String): NetWorkPokemon
}