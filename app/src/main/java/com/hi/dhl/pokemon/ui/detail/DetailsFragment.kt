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

package com.hi.dhl.pokemon.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.commit
import androidx.lifecycle.Observer
import com.hi.dhl.jdatabinding.DataBindingFragment
import com.hi.dhl.pokemon.R
import com.hi.dhl.pokemon.databinding.FragmentDetailsBinding
import com.hi.dhl.pokemon.model.PokemonItemModel

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/7/22
 *     desc  : 演示的是使用带参数的 Fragment
 * </pre>
 */

// 如果使用带参数的 Fragment 需要设置 FragmentFactory，告诉系统如何实例化 Fragment
class DetailsFragment(args: String) : DataBindingFragment() {

    private lateinit var mBinding: FragmentDetailsBinding
    private val mViewModel: DetailViewModel by activityViewModels()
    private lateinit var mPokemonModel: PokemonItemModel
    val mAlbumAdapter: AlbumAdapter by lazy { AlbumAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mPokemonModel = requireNotNull(arguments?.getParcelable(KEY_LIST_MODEL)) {
            "params is not null"
        }
        mBinding = binding(inflater, R.layout.fragment_details, container)

        return mBinding.apply {
            pokemonListModel = mPokemonModel
            albumAdapter = mAlbumAdapter
            viewModel = mViewModel.apply {
                fectchPokemonInfo2(mPokemonModel.name)
                    .observe(viewLifecycleOwner, Observer {})
            }
            lifecycleOwner = this@DetailsFragment
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mViewModel.failure.observe(viewLifecycleOwner, Observer {
            Toast.makeText(context, it, Toast.LENGTH_LONG).show()
        })

        /**
         * 这三种方式的使用和 之前在 [DetailActivity] 使用方式一样
         */

//        // 方法一
//        mViewModel.pokemon.observe(this, Observer {
//            // 将数据显示在页面上
//        })
//
//        // 方法二
//        mViewModel.fectchPokemonInfo2(mPokemonModel.name).observe(this, Observer {
//            // 将数据显示在页面上
//        })
//
//        // 方法三
//        lifecycleScope.launch {
//            mViewModel.apply {
//                fectchPokemonInfo3(mPokemonModel.name).observe(viewLifecycleOwner, Observer {
//                    // 将数据显示在页面上
//                })
//            }
//        }
    }

    companion object {
        private val KEY_LIST_MODEL = "listModel"

        fun addFragment(
            manager: FragmentManager,
            params: PokemonItemModel,
            fragmentContainerId: Int
        ) {

            manager.commit {
                val bundle = bundleOf(KEY_LIST_MODEL to params)
                replace(fragmentContainerId, DetailsFragment::class.java, bundle)
            }
        }
    }
}