@file:Suppress("unused")

package ir.farsroidx.m31.additives

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import androidx.annotation.*
import androidx.core.content.res.ResourcesCompat
import org.xmlpull.v1.XmlPullParser
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.util.*

// TODO: Resource ===================================================================== Resource ===

fun Context.getStringResource(@StringRes resId: Int) = getString(resId)

fun Context.getIntArrayResource(@ArrayRes resId: Int) = resources.getIntArray(resId)

fun Context.getStringArrayResource(@ArrayRes resId: Int): Array<String> = resources.getStringArray(resId)

fun Context.getTextArrayResource(@ArrayRes resId: Int): Array<CharSequence> = resources.getTextArray(resId)

fun Context.getDimensionResource(@DimenRes resId: Int) = resources.getDimension(resId)

fun Context.getColorResource(@ColorRes resId: Int) = ResourcesCompat.getColor(resources, resId, null)

fun Context.getTypeFace(@FontRes resId: Int) = ResourcesCompat.getFont(this, resId)

fun Context.getTextFromAssets(
    path: String,
    exception: (e: Exception) -> Unit = {}
): String {
    val stringBuilder = StringBuilder()
    var reader: BufferedReader? = null
    try {
        reader = BufferedReader(
            InputStreamReader(
                this.assets.open(path),
                "UTF-8"
            )
        )
        reader.forEachLine {
            stringBuilder.append(it)
            stringBuilder.append('\n')
        }
    } catch (e: IOException) {
        e.printStackTrace()
        exception(e)
    } finally {
        if (reader != null) {
            try {
                reader.close()
            } catch (e: IOException) {
                e.printStackTrace()
                exception(e)
            }
        }
    }
    return stringBuilder.toString().trim()
}

fun Context.loadFileProperties(name: String): Properties {
    @SuppressLint("DiscouragedApi")
    val properties = Properties().apply {
        load(
            resources.openRawResource(
                resources.getIdentifier(
                    name, "raw", packageName
                )
            )
        )
    }
    return properties
}

fun Context.loadFileXML(resId: Int): XmlPullParser {
    return resources.getXml(resId)
}

fun String.hexColor(): Int {
    return Color.parseColor("#$this")
}