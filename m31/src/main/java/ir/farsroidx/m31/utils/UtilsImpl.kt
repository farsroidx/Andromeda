package ir.farsroidx.m31.utils

import android.content.Context
import ir.farsroidx.m31.utils.clipboard.Clipboard
import ir.farsroidx.m31.utils.clipboard.ClipboardImpl

internal class UtilsImpl(
    context: Context,
    private val config: UtilsConfig,
) : Utils {

    override val clipboard: Clipboard = ClipboardImpl( context )

}