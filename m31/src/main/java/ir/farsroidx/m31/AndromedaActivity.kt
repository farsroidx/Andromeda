@file:Suppress("unused", "UNUSED_PARAMETER", "MemberVisibilityCanBePrivate")

package ir.farsroidx.m31

import android.content.Intent
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.annotation.CallSuper
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.ViewDataBinding
import ir.farsroidx.m31.additives.autoViewDataBinding
import java.io.Serializable

abstract class AndromedaActivity <VDB: ViewDataBinding> : AppCompatActivity() {

    private var _dataBinding: VDB? = null
    protected val dataBinding: VDB by lazy { _dataBinding!! }

    protected var isRtlDirection = true

    protected var enterAnimation = android.R.anim.fade_in
    protected var exitAnimation  = android.R.anim.fade_out

    protected var useTransitionAnimation = true

    override fun onCreate(savedInstanceState: Bundle?) {

        onBeforeInitializing(savedInstanceState)

        window.decorView.layoutDirection =
            if (isRtlDirection) View.LAYOUT_DIRECTION_RTL
            else View.LAYOUT_DIRECTION_LOCALE

        super.onCreate(savedInstanceState)

        // Auto DataBinding
        _dataBinding = autoViewDataBinding()

        onBackPressedDispatcher.addCallback(this, object: OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                onBackStackPressed()
            }
        })

        onInitialized()
    }

    /** Before onCreate called */
    protected open fun onBeforeInitializing(savedInstanceState: Bundle?) {

    }

    /** After onCreate called */
    protected abstract fun onInitialized()

    @CallSuper
    protected open fun onBackStackPressed() {
        finish()
        runTransitionAnimation()
    }

    fun onBackPressedFromXml(view: View) {
        onBackStackPressed()
    }

    protected inline fun <reified T : Any> getIntentData(
        key: String,
        alternative: T? = null,
        serializableClass: Class<Serializable>? = null
    ): T {
        intent.apply {
            return when(alternative) {
                is Boolean -> { getBooleanExtra(key, alternative) as T }
                is Byte -> { getByteExtra(key, alternative) as T  }
                is Char -> { getCharExtra(key, alternative) as T  }
                is Short -> { getShortExtra(key, alternative) as T  }
                is Int -> { getIntExtra(key, alternative) as T  }
                is Long -> { getLongExtra(key, alternative) as T  }
                is Float -> { getFloatExtra(key, alternative) as T  }
                is Double -> { getDoubleExtra(key, alternative) as T  }
                is String -> { getStringExtra(key) as T  }
                is CharSequence -> { getCharSequenceExtra(key) as T }
                is Serializable -> {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                        if (serializableClass != null) {
//                            this.getSerializableExtra(key, alternative::class.java as Class<Serializable>) as T
                            getSerializableExtra(key, serializableClass) as T
                        } else {
                            throw AndromedaException("Serializable variable is null.")
                        }
                    } else {
                        getSerializableExtra(key) as T
                    }
                }
                else -> {
                    throw AndromedaException("Type of request not supported!")
                }
            }
        }
    }

    fun launchActivity(
        clazz: Class<*>,
        extras: Map<String, Any>? = null,
        intent: (Intent) -> Unit = {},
        withFinish: Boolean = false,
    ) {
        Intent(this, clazz).apply {
            extras?.forEach { (key, value) ->
                when(value) {
                    is Boolean -> { this.putExtra(key, value) }
                    is Byte -> { this.putExtra(key, value) }
                    is Char -> { this.putExtra(key, value) }
                    is Short -> { this.putExtra(key, value) }
                    is Int -> { this.putExtra(key, value) }
                    is Long -> { this.putExtra(key, value) }
                    is Float -> { this.putExtra(key, value) }
                    is Double -> { this.putExtra(key, value) }
                    is String -> { this.putExtra(key, value) }
                    is CharSequence -> { this.putExtra(key, value) }
                    is Serializable -> { this.putExtra(key, value) }
                    else -> {
                        throw AndromedaException(
                            "Type of key: $key, value: $value not supported!"
                        )
                    }
                }
            }
            intent( this )
            startActivity(this)
            if (withFinish) { finish() }
        }
        runTransitionAnimation()
    }

    fun launchAppSettingsPage() {
        startActivity(
            Intent(
                Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                Uri.fromParts(
                    "package",
                    packageName,
                    null
                )
            )
        )
        runTransitionAnimation()
    }

    private fun runTransitionAnimation() {
        if (useTransitionAnimation) {
            overridePendingTransition(
                enterAnimation, exitAnimation
            )
        }
    }

    protected fun getColorRes(@ColorRes resId: Int): Int {
        return ContextCompat.getColor(this, resId)
    }

    protected fun getStringRes(@StringRes resId: Int): String {
        return getString(resId)
    }

    protected fun getDrawableRes(@DrawableRes resId: Int): Drawable? {
        return ContextCompat.getDrawable(this, resId)
    }

    override fun onResume() {
        super.onResume()
        AndromedaState.currentActivity = this
    }

    override fun onPause() {
        super.onPause()
        AndromedaState.currentActivity = null
    }
}
