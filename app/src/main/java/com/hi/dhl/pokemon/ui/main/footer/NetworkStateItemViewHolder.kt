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

package com.hi.dhl.pokemon.ui.main.footer

import android.view.View
import androidx.paging.LoadState
import com.hi.dhl.jdatabinding.DataBindingViewHolder
import com.hi.dhl.pokemon.databinding.RecycieItemNetworkStateBinding

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/7/11
 *     desc  :
 * </pre>
 */
class NetworkStateItemViewHolder(view: View, private val retryCallback: () -> Unit) :
    DataBindingViewHolder<LoadState>(view) {
    val mBinding: RecycieItemNetworkStateBinding by viewHolderBinding(view)

    override fun bindData(data: LoadState, position: Int) {
        mBinding.apply {
            // 正在加载，显示进度条
            progress.isVisible = data is LoadState.Loading
            // 加载失败，显示并点击重试按钮
            retryButton.isVisible = data is LoadState.Error
            retryButton.setOnClickListener { retryCallback() }
            // 加载失败显示错误原因
            errorMsg.isVisible = !(data as? LoadState.Error)?.error?.message.isNullOrBlank()
            errorMsg.text = (data as? LoadState.Error)?.error?.message

            executePendingBindings()
        }
    }
}

inline var View.isVisible: Boolean
    get() = visibility == View.VISIBLE
    set(value) {
        visibility = if (value) View.VISIBLE else View.GONE
    }