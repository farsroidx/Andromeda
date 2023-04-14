@file:Suppress("unused", "REDUNDANT_ELSE_IN_WHEN")

package ir.farsroidx.m31.additives

import ir.farsroidx.m31.Andromeda
import ir.farsroidx.m31.AndromedaApplication
import ir.farsroidx.m31.AndromedaConfig
import ir.farsroidx.m31.AndromedaTimeUnit

// TODO: Common ================================================================================ ///

internal const val PREFERENCE_NAME = "andromeda-preferences"

internal inline fun <reified T> isCasted(instance: Any?) = ( instance is T )

internal fun Int?.toExpirationTime(unit: AndromedaTimeUnit? = null): Long? {
    if (this == null || unit == null) return null
    val seconds = this * unit.value
    return ( System.currentTimeMillis() / 1_000L ) + seconds
}

internal fun Long?.isExpired(): Boolean {
    if (this == null) return false
    return ( System.currentTimeMillis() / 1_000L ) > this
}

internal fun Andromeda.install(
    application: AndromedaApplication, configs: List<AndromedaConfig>
) {
    initializedKoin(
        application, configs
    )
}