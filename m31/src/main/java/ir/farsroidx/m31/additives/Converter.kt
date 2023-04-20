@file:Suppress("unused", "UNUSED_PARAMETER", "MemberVisibilityCanBePrivate")

package ir.farsroidx.m31.additives

import android.content.res.Resources
import android.graphics.Color
import android.os.Build
import android.text.Html
import android.text.Spanned
import android.util.Base64
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.text.DecimalFormat

// TODO: Converter =================================================================== Converter ===

val Int.px: Int
    get() = (this * Resources.getSystem().displayMetrics.density).toInt()

val Int.dp: Int
    get() = (this / Resources.getSystem().displayMetrics.density).toInt()

fun String.htmlToSpannable(): Spanned {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        Html.fromHtml(this, Html.FROM_HTML_MODE_LEGACY)
    } else {
        @Suppress("DEPRECATION")
        Html.fromHtml(this)
    }
}

fun Spanned.htmlFromString(): String {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        Html.toHtml(this, Html.FROM_HTML_MODE_LEGACY)
    } else {
        @Suppress("DEPRECATION")
        Html.toHtml(this)
    }
}

fun Number.toRialFormat(): String {
    return DecimalFormat("###,###,###")
        .format(this)
}

fun String?.toRialFormat(): String {
    return DecimalFormat("###,###,###")
        .format(
            this ?: 0
        )
}

fun String.encodeBase64(): String {
    return Base64.encodeToString(
        this.toByteArray(
            charset("UTF-8")
        ), Base64.DEFAULT)
}

fun String.decodeBase64(): String {
    return Base64.decode(this, Base64.DEFAULT)
        .toString(
            charset("UTF-8")
        )
}

fun String.md5(): String {

    try {

        val digest = MessageDigest.getInstance("MD5")

        digest.update(this.toByteArray())

        val messageDigest = digest.digest()

        val hexString = StringBuilder()

        for (aMessageDigest in messageDigest) {
            var h = Integer.toHexString(0xFF and aMessageDigest.toInt())
            while (h.length < 2) h = "0$h"
            hexString.append(h)
        }

        return hexString.toString()

    } catch (e: NoSuchAlgorithmException) {
        e.printStackTrace()
    }

    return ""
}

fun String.convertToColor(): Int {
    return Color.parseColor(this)
}

fun substring(string: String, startPosition: Int, readRow: Int = 1): String {
    return string.substring((startPosition - 1) , (startPosition - 1) + readRow)
}

fun substringAsInt(string: String, startPosition: Int, readRow: Int = 1): Int {
    return string.substring((startPosition - 1) , (startPosition - 1) + readRow).toInt()
}