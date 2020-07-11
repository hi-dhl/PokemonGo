package com.hi.dhl.pokemon.ui.detail

import android.content.Context
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.hi.dhl.jdatabinding.DataBindingAppCompatActivity
import com.hi.dhl.pokemon.R
import com.hi.dhl.pokemon.databinding.ActivityDetailsBinding
import com.hi.dhl.pokemon.model.PokemonListModel
import dagger.hilt.android.AndroidEntryPoint
import org.jetbrains.anko.startActivity

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/7/7
 *     desc  :
 * </pre>
 */

@AndroidEntryPoint
class DetailActivity : DataBindingAppCompatActivity() {

    private val mBindingActivity: ActivityDetailsBinding by binding(R.layout.activity_details)
    private val mViewModel: DetailViewModel by viewModels()
    lateinit var mPokemonModel: PokemonListModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBindingActivity.apply {
            mPokemonModel = requireNotNull(intent.getParcelableExtra(KEY_LIST_MODEL))
            pokemonListModel = mPokemonModel
            lifecycleOwner = this@DetailActivity
            viewModel = mViewModel.apply {
                fectchPokemonInfo(mPokemonModel.name)
                    .observe(this@DetailActivity, Observer {})
            }
        }
    }

    companion object {
        private val TAG = "DetailActivity"
        private val KEY_LIST_MODEL = "listModel"
        fun jumpAcrtivity(act: Context, params: PokemonListModel) {
            act.startActivity<DetailActivity>(KEY_LIST_MODEL to params)
        }
    }
}