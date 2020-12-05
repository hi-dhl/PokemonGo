package com.hi.dhl.pokemon.ui.detail

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/7/22
 *     desc  :
 * </pre>
 */
class CustomFragmentFactory() : FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment =
        when (className) {
            DetailsFragment::class.java.name -> DetailsFragment(DetailsFragment::class.java.simpleName)
            else -> super.instantiate(classLoader, className)
        }
}