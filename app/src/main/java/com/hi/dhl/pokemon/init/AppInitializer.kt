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