package ir.farsroidx.m31.utils.clipboard

import android.content.*
import android.net.Uri
import android.os.Build
import android.view.textclassifier.TextLinks
import androidx.annotation.RequiresApi

internal class ClipboardImpl(
    private val context: Context
) : Clipboard {

    private val clipboardManager by lazy {
        context.getSystemService(Context.CLIPBOARD_SERVICE)
                as ClipboardManager
    }

    override fun clipboardItemCount(): Int {
        return clipboardManager.primaryClip?.itemCount ?: -1
    }

    override fun copyCharSequence(label: CharSequence, data: CharSequence): Boolean {
        return copyData(
            ClipData.newPlainText(
                label, data
            )
        )
    }

    override fun copyHtmlText(label: CharSequence, text: CharSequence, html: String): Boolean {
        return copyData(
            ClipData.newHtmlText(
                label, text, html
            )
        )
    }

    override fun copyIntent(label: CharSequence, intent: Intent): Boolean {
        return copyData(
            ClipData.newIntent(
                label, intent
            )
        )
    }

    override fun copyUri(resolver: ContentResolver, label: CharSequence, uri: Uri): Boolean {
        return copyData(
            ClipData.newUri(
                resolver, label, uri
            )
        )
    }

    override fun copyRawUri(label: CharSequence, uri: Uri): Boolean {
        return copyData(
            ClipData.newRawUri(
                label, uri
            )
        )
    }

    private fun copyData(data: ClipData): Boolean {
        return try {
            clipboardManager.setPrimaryClip( data )
            true
        } catch (th: Throwable) {
            th.printStackTrace()
            false
        }
    }

    override fun isPasteEnable(): Boolean {
        if (!clipboardManager.hasPrimaryClip()) {
            return false
        } else {
            val hasMimeType = clipboardManager.primaryClipDescription?.hasMimeType(
                ClipDescription.MIMETYPE_TEXT_PLAIN
            )
            if (hasMimeType == null || !hasMimeType) {
                return false
            }
            return true
        }
    }

    override fun pasteCharSequence(): CharSequence? = pasteData()?.text

    override fun pasteUri(): Uri? = pasteData()?.uri

    override fun pasteIntent(): Uri? = pasteData()?.uri

    override fun pasteHtmlText(): String? = pasteData()?.htmlText

    @RequiresApi(Build.VERSION_CODES.S)
    override fun pasteTextLinks(): TextLinks? = pasteData()?.textLinks

    private fun pasteData(): ClipData.Item? {
        return try {
            if (isPasteEnable() && clipboardItemCount() > 0) {
                return clipboardManager.primaryClip?.getItemAt(0)
            }
            return null
        } catch (th: Throwable) {
            th.printStackTrace()
            null
        }
    }
}