@file:Suppress("unused", "MemberVisibilityCanBePrivate")

package ir.farsroidx.m31

import android.app.Application
import android.content.Context
import androidx.annotation.CallSuper
import androidx.multidex.MultiDex
import ir.farsroidx.m31.additives.install

abstract class AndromedaApplication : Application() {

    abstract val andromedaModules: List<AndromedaConfig>

    override fun onCreate() {
        super.onCreate()

        // Start Andromeda
        Andromeda.install(this, andromedaModules)
    }

    @CallSuper
    override fun attachBaseContext(context: Context) {
        super.attachBaseContext(context)
        MultiDex.install(this)
    }
}