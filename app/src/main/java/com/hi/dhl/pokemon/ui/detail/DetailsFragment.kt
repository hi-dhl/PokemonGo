package com.hi.dhl.pokemon.ui.detail

import android.os.Bundle
import android.view.View
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
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/7/22
 *     desc  : 演示的是使用带参数的 Fragment
 * </pre>
 */

// 如果使用带参数的 Fragment 需要设置 FragmentFactory，告诉系统如何实例化 Fragment
@FlowPreview
@ExperimentalCoroutinesApi
class DetailsFragment(args: String) : DataBindingFragment(R.layout.fragment_details) {

    private val mBinding: FragmentDetailsBinding by binding()
    private val mViewModel: DetailViewModel by activityViewModels()
    private lateinit var mPokemonModel: PokemonItemModel
    val mAlbumAdapter: AlbumAdapter by lazy { AlbumAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mPokemonModel = requireNotNull(arguments?.getParcelable(KEY_LIST_MODEL)) {
            "params is not null"
        }

        mBinding.apply {
            pokemonListModel = mPokemonModel
            albumAdapter = mAlbumAdapter
            viewModel = mViewModel.apply {
                fectchPokemonInfo2(mPokemonModel.name)
                    .observe(viewLifecycleOwner, Observer {})
            }
            lifecycleOwner = this@DetailsFragment
        }

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