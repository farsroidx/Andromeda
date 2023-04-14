package ir.farsroidx.m31.app

import java.util.*

interface App {

    fun setLanguage(iLocale: Locale)

    fun setNightMode(@AppNightMode mode: Int)

    fun killProcess()

}