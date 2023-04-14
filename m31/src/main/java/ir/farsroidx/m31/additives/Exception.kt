package ir.farsroidx.m31.additives

import android.text.SpannableStringBuilder
import android.util.Patterns
import ir.farsroidx.m31.AndromedaException
import kotlin.system.exitProcess

// TODO: Exception ============================================================================= ///

internal fun SpannableStringBuilder.appendByLine(value: CharSequence, enterCount: Int = 1): SpannableStringBuilder {
    this.append(value)
    for (i in 0 until enterCount) { this.append("\n") }
    return this
}

internal fun killApplication() {
    android.os.Process.killProcess(
        android.os.Process.myPid()
    )
    exitProcess(10)
}

internal fun validationEmail(email: String) {
    val isSuccess = Patterns.EMAIL_ADDRESS.matcher(email).matches()
    if (!isSuccess) {
        throw AndromedaException("Developer Email is Invalid")
    }
}