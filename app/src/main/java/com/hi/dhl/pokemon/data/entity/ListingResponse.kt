package com.hi.dhl.pokemon.data.entity

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/7/6
 *     desc  :
 * </pre>
 */

data class ListingResponse(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<ListingData>
)

data class ListingData(
    var page: Int = 0,
    val name: String,
    val url: String
) {
    fun getImageUrl(): String {
        val index = url.split("/".toRegex()).dropLast(1).last()
        return "https://pokeres.bastionbot.org/images/pokemon/$index.png"
    }

    override fun toString(): String {
        return "ListingData(page=$page, name='$name', url='$url')"
    }
}