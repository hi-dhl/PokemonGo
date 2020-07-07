package com.hi.dhl.pokemon.ui.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.api.load

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/7/7
 *     desc  :
 * </pre>
 */

@BindingAdapter("bindingAvator")
fun bindingAvator(imageView: ImageView, url: String) {
    imageView.load(url){
        crossfade(true)
    }
}