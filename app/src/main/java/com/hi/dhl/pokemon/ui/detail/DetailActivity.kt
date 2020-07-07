package com.hi.dhl.pokemon.ui.detail

import android.app.Activity
import android.content.Context
import android.os.Bundle
import com.hi.dhl.jdatabinding.DataBindingAppCompatActivity
import com.hi.dhl.pokemon.R
import com.hi.dhl.pokemon.databinding.ActivityDetailsBinding
import com.hi.dhl.pokemon.model.PokemonInfoModel
import com.hi.dhl.pokemon.model.PokemonListModel
import org.jetbrains.anko.startActivity

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/7/7
 *     desc  :
 * </pre>
 */
class DetailActivity : DataBindingAppCompatActivity() {

    val mBindingActivity: ActivityDetailsBinding by binding(R.layout.activity_details)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBindingActivity.apply {
            pokemonListModel = intent.extras?.getParcelable(KEY_LIST_MODEL)
            lifecycleOwner = this@DetailActivity
        }
    }

    companion object {
        private val KEY_LIST_MODEL = "listModel"
        fun jumpAcrtivity(act: Context, params: PokemonListModel) {
            act.startActivity<DetailActivity>(KEY_LIST_MODEL to params)
        }
    }
}