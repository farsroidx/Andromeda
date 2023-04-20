@file:Suppress("unused", "MemberVisibilityCanBePrivate")

package ir.farsroidx.m31

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import ir.farsroidx.m31.additives.autoViewDataBinding

abstract class AndromedaFragment <VDB: ViewDataBinding> : Fragment() {

    private var _dataBinding: VDB? = null
    protected val dataBinding: VDB by lazy { _dataBinding!! }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (_dataBinding == null) {
            _dataBinding = autoViewDataBinding()
            onInitialized( savedInstanceState )
        }
        onReInitializing( savedInstanceState )
        return dataBinding.root
    }

    /** After onCreate called */
    protected abstract fun onInitialized(savedInstanceState: Bundle?)

    /** It is called every time the fragment is created */
    protected open fun onReInitializing(savedInstanceState: Bundle?) {

    }

    protected fun getColorRes(@ColorRes resId: Int): Int {
        return ContextCompat.getColor(requireContext(), resId)
    }

    protected fun getStringRes(@StringRes resId: Int): String {
        return getString(resId)
    }

    protected fun getDrawableRes(@DrawableRes resId: Int): Drawable? {
        return ContextCompat.getDrawable(requireContext(), resId)
    }

    override fun onResume() {
        super.onResume()
        AndromedaState.currentFragment = this
    }

    override fun onPause() {
        super.onPause()
        AndromedaState.currentFragment = null
    }
}