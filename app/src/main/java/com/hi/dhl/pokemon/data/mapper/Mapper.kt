package com.hi.dhl.pokemon.data.mapper

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/7/11
 *     desc  :
 * </pre>
 */
interface Mapper<I, O> {
    fun map(input: I): O
}