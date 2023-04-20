@file:Suppress("unused")

package ir.farsroidx.m31.additives

import android.util.Log
import ir.farsroidx.m31.AndromedaState

// TODO: Log =============================================================================== Log ===

private fun Any?.isAvailable() = ( AndromedaState.logEnabled && this != null )

fun vLog(log: Any?) {
    if ( log.isAvailable() ) {
        Log.v(AndromedaState.logStrTag, log.toString())
    }
}

fun iLog(log: Any?) {
    if ( log.isAvailable() ) {
        Log.i(AndromedaState.logStrTag, log.toString())
    }
}

fun dLog(log: Any?) {
    if ( log.isAvailable() ) {
        Log.d(AndromedaState.logStrTag, log.toString())
    }
}

fun wLog(log: Any?) {
    if ( log.isAvailable() ) {
        Log.w(AndromedaState.logStrTag, log.toString())
    }
}

fun eLog(log: Any?) {
    if ( log.isAvailable() ) {
        Log.e(AndromedaState.logStrTag, log.toString())
    }
}
