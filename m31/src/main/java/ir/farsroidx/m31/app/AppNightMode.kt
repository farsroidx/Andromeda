package ir.farsroidx.m31.app

import androidx.annotation.IntDef
import androidx.appcompat.app.AppCompatDelegate

@IntDef(
    value = [
        AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM,
        AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY,
        AppCompatDelegate.MODE_NIGHT_UNSPECIFIED,
        AppCompatDelegate.MODE_NIGHT_YES,
        AppCompatDelegate.MODE_NIGHT_NO,
    ]
)
@Retention(AnnotationRetention.SOURCE)
@Target(AnnotationTarget.VALUE_PARAMETER)
annotation class AppNightMode
