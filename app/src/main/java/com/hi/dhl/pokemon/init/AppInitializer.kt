package com.hi.dhl.pokemon.init

import android.content.Context
import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import android.os.StrictMode.VmPolicy
import androidx.startup.Initializer
import com.hi.dhl.pokemon.AppHelper
import com.hi.dhl.pokemon.BuildConfig
import timber.log.Timber
import timber.log.Timber.DebugTree

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/7/11
 *     desc  :
 * </pre>
 */
class AppInitializer : Initializer<Unit> {
    override fun create(context: Context) {
        if (!BuildConfig.DEBUG) {
            return
        }
        StrictMode.setThreadPolicy(
            ThreadPolicy.Builder().detectAll().penaltyLog().build()
        )
        StrictMode.setVmPolicy(VmPolicy.Builder().detectAll().penaltyLog().build())
        Timber.plant(DebugTree())
        AppHelper.init(context)
        return Unit
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> = mutableListOf()
}