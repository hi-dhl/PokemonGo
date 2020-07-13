package com.hi.dhl.pokemon.ui.binding

import android.app.Activity
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import coil.api.load
import com.hi.dhl.jprogressview.JProgressView
import com.hi.dhl.pokemon.R
import com.hi.dhl.pokemon.model.PokemonListModel
import com.hi.dhl.pokemon.ui.detail.DetailActivity
import timber.log.Timber

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/7/7
 *     desc  :
 * </pre>
 */

@BindingAdapter("bindingAvator")
fun bindingAvator(imageView: ImageView, url: String) {
    imageView.load(url) {
        crossfade(true)
        placeholder(R.mipmap.ic_launcher_round)
    }
}

@BindingAdapter("bindLoading")
fun bindingLoading(swipe: SwipeRefreshLayout, isLoading: Boolean) {
    Timber.tag("bindingLoading").e(" isLoading = ${isLoading}")
    swipe.isRefreshing = isLoading
    if (!isLoading) swipe.isEnabled = false
}

@BindingAdapter("progressValue", "maxProgressValue")
fun bindingProgressView(progress: JProgressView, progressValue: Int, maxProgressValue: Int) {
    progress
        .setProgress(progressValue.toFloat())
        .setMaxProgress(maxProgressValue)
        .startAnimal()
}

@BindingAdapter("finish")
fun bindingFinish(view: View, finish: Boolean) {
    val ctx = view.context
    if (ctx is Activity && finish) {
        view.setOnClickListener { ctx.finish() }
    }
}

@BindingAdapter("bindClick")
fun bindingClick(view: View, model: PokemonListModel) {
    view.setOnClickListener {
        DetailActivity.jumpAcrtivity(
            view.context,
            model
        )
    }
}