package ir.farsroidx.m31.utils.clipboard

import android.content.ContentResolver
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.view.textclassifier.TextLinks
import androidx.annotation.RequiresApi

interface Clipboard {

    fun clipboardItemCount(): Int

    fun copyCharSequence(label: CharSequence, data: CharSequence): Boolean

    fun copyHtmlText(label: CharSequence, text: CharSequence, html: String): Boolean

    fun copyIntent(label: CharSequence, intent: Intent): Boolean

    fun copyUri(resolver: ContentResolver, label: CharSequence, uri: Uri): Boolean

    fun copyRawUri(label: CharSequence, uri: Uri): Boolean

    fun isPasteEnable(): Boolean

    fun pasteCharSequence(): CharSequence?

    fun pasteUri(): Uri?

    fun pasteIntent(): Uri?

    fun pasteHtmlText(): String?

    @RequiresApi(Build.VERSION_CODES.S)
    fun pasteTextLinks(): TextLinks?

}