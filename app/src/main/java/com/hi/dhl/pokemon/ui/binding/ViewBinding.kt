package com.hi.dhl.pokemon.ui.binding

import android.animation.ValueAnimator
import android.graphics.Color
import android.graphics.drawable.ClipDrawable
import android.graphics.drawable.ColorDrawable
import android.text.TextUtils
import android.view.Gravity
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import coil.api.load
import com.hi.dhl.pokemon.R
import com.hi.dhl.pokemon.ext.visible
import com.hi.dhl.pokemon.model.PokemonInfoModel
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
    }
}

@BindingAdapter("bindLoading")
fun bindingLoading(swipe: SwipeRefreshLayout, isLoading: Boolean) {
    Timber.tag("bindingLoading").e(" isLoading = ${isLoading}")
    swipe.isRefreshing = isLoading
    if (!isLoading) swipe.isRefreshing = false
}

//@BindingAdapter("bindingProgress")
//fun bindingProgress(
//    progressBar: ProgressBar,
//    pokemonInfoModel: PokemonInfoModel
//) {
//    progressBar.visible()
//    progressBar.max = pokemonInfoModel.max
//    progressBar.setBackgroundResource(R.color.color_progress_bg) // 背景色
//
//    // 前景色
////        if (TextUtils.isEmpty(lableColor) || lableColor.length <= 0) return
//    val drawable = ClipDrawable(
//        ColorDrawable(Color.RED),
//        Gravity.LEFT,
//        ClipDrawable.HORIZONTAL
//    )
//    progressBar.setProgressDrawable(drawable)
//
//    // 开启动画
//    val valueAnimator: ValueAnimator = ValueAnimator.ofInt(0, pokemonInfoModel.progress)
//    valueAnimator.setDuration(500)
//    valueAnimator.addUpdateListener(object : ValueAnimator.AnimatorUpdateListener {
//        override fun onAnimationUpdate(animation: ValueAnimator) {
//            var current = animation.animatedValue as Int
//            progressBar.setProgress(current)
//        }
//
//    })
//    valueAnimator.start()
//
//}