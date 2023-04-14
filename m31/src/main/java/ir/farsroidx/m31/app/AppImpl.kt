@file:Suppress("DEPRECATION")

package ir.farsroidx.m31.app

import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import ir.farsroidx.m31.additives.killApplication
import java.util.*

internal class AppImpl(
    private val context: Context,
) : App {

    override fun setLanguage(iLocale: Locale) {
        context.resources.updateConfiguration(
            context.resources.configuration.apply {
                locale = iLocale
            },
            context.resources.displayMetrics
        )
    }

    // set auto save pref
    override fun setNightMode(@AppNightMode mode: Int) {
        AppCompatDelegate.setDefaultNightMode(mode)
    }

    override fun killProcess() {
        killApplication()
    }
}