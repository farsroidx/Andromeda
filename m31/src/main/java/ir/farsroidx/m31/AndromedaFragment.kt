package ir.farsroidx.m31

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import ir.farsroidx.m31.additives.autoViewDataBinding

@Suppress("MemberVisibilityCanBePrivate")
abstract class AndromedaFragment <VB: ViewDataBinding> : Fragment() {

    private var _dataBinding: VB? = null
    protected val dataBinding: VB by lazy { _dataBinding!! }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (_dataBinding == null) {
            _dataBinding = autoViewDataBinding()
            onInitialized()
        }
        onReInitializing()
        return dataBinding.root
    }

    /** After onCreate called */
    protected abstract fun onInitialized()

    /** It is called every time the fragment is created */
    protected open fun onReInitializing() {

    }
}