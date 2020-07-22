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

package com.hi.dhl.pokemon.ui.binding

import android.app.Activity
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import coil.api.load
import com.hi.dhl.jprogressview.JProgressView
import com.hi.dhl.pokemon.R
import com.hi.dhl.pokemon.model.PokemonInfoModel
import com.hi.dhl.pokemon.model.PokemonItemModel
import com.hi.dhl.pokemon.ui.detail.AlbumAdapter
import com.hi.dhl.pokemon.ui.detail.DetailActivity
import timber.log.Timber

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/7/11
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

@BindingAdapter("bindSmallImage")
fun bindingSmallImage(imageView: ImageView, url: String) {
    imageView.load(url) {
        crossfade(true)
        placeholder(R.mipmap.ic_launcher_round)
        size(280,280)
    }
}

@BindingAdapter("bindLoading")
fun bindingLoading(swipe: SwipeRefreshLayout, isLoading: Boolean) {
    Timber.tag("bindingLoading").e(" isLoading = ${isLoading}")
    swipe.isRefreshing = isLoading
    if (!isLoading) swipe.isEnabled = false
}

@BindingAdapter("bindProgressValue", "bindProgressMaxValue")
fun bindingProgressView(progress: JProgressView, progressValue: Int, maxProgressValue: Int) {
    progress
        .setProgress(progressValue.toFloat())
        .setMaxProgress(maxProgressValue)
        .startAnimal()
}

@BindingAdapter("bindFinish")
fun bindingFinish(view: View, finish: Boolean) {
    val ctx = view.context
    if (ctx is Activity && finish) {
        view.setOnClickListener { ctx.finish() }
    }
}

@BindingAdapter("bindClick")
fun bindingClick(view: View, model: PokemonItemModel) {
    view.setOnClickListener {
        DetailActivity.jumpAcrtivity(
            view.context,
            model
        )
    }
}

@BindingAdapter("bindAdapter", "bindData")
fun bindingAdapter(
    recyclerView: RecyclerView,
    albumAdapter: AlbumAdapter,
    data: List<PokemonInfoModel.AlbumModel>?
) {
    data?.let {
        recyclerView.adapter = albumAdapter
        albumAdapter.submitList(data)
        albumAdapter.notifyDataSetChanged()
    }

}