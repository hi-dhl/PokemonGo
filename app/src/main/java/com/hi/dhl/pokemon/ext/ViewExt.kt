package com.hi.dhl.pokemon.ext

import android.view.View

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/7/10
 *     desc  :
 * </pre>
 */

fun View.visible() {
    visibility = View.VISIBLE
}

/** makes gone a view. */
fun View.gone() {
    visibility = View.GONE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}
