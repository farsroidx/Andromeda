package ir.farsroidx.m31

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding
import ir.farsroidx.m31.additives.autoViewDataBinding

@Suppress("MemberVisibilityCanBePrivate")
abstract class AndromedaActivity <VB: ViewDataBinding> : AppCompatActivity() {

    protected lateinit var dataBinding: VB

    override fun onCreate(savedInstanceState: Bundle?) {

        window.decorView.layoutDirection = View.LAYOUT_DIRECTION_RTL

        super.onCreate(savedInstanceState)

        // Auto DataBinding
        dataBinding = autoViewDataBinding()

        onInitialized()
    }

    /** After onCreate called */
    protected abstract fun onInitialized()

}
