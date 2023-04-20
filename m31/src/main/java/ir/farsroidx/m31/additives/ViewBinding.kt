@file:Suppress("unused", "DiscouragedApi", "UNCHECKED_CAST")

package ir.farsroidx.m31.additives

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import ir.farsroidx.m31.AndromedaActivity
import ir.farsroidx.m31.AndromedaFragment
import ir.farsroidx.m31.AndromedaRecyclerAdapter
import java.lang.reflect.ParameterizedType
import java.util.*

// TODO: ViewBinding =============================================================== ViewBinding ===

/** Convert a camel case string to snake case. */
private val camelRegex = "(?<=[a-zA-Z])[A-Z]".toRegex()
internal fun String.toSnakeCase(): String {
    return camelRegex.replace(this) {
        "_${it.value}"
    }.lowercase(Locale.getDefault())
}

/** Automatically sets (ViewDataBinding) using generics detection */
internal fun <T: ViewDataBinding> AndromedaActivity<*>.autoViewDataBinding(): T {

    val persistentClass : Class<T> = ( javaClass.genericSuperclass as ParameterizedType)
        .actualTypeArguments[0] as Class<T>

    val layoutName = persistentClass.simpleName.toSnakeCase().substringBeforeLast("_")
    val layoutResId = resources.getIdentifier(layoutName, "layout", packageName)

    return DataBindingUtil.inflate<T>(layoutInflater, layoutResId, null, false)
        .apply {
            lifecycleOwner = this@autoViewDataBinding
            setContentView( root )
        }
}

/** Automatically sets (ViewDataBinding) using generics detection */
internal fun <T: ViewDataBinding> AndromedaFragment<*>.autoViewDataBinding(): T {

    val persistentClass : Class<T> = ( javaClass.genericSuperclass as ParameterizedType)
        .actualTypeArguments[0] as Class<T>

    val layoutName = persistentClass.simpleName.toSnakeCase().substringBeforeLast("_")
    val layoutResId = resources.getIdentifier(layoutName, "layout", requireActivity().packageName)

    return DataBindingUtil.inflate<T>(layoutInflater, layoutResId, null, false)
        .apply {
            lifecycleOwner = this@autoViewDataBinding
        }
}

/** Automatically sets (ViewDataBinding) using generics detection */
internal fun <T: ViewDataBinding> AndromedaRecyclerAdapter<*, *>.autoViewDataBinding(
    context: Context, layoutInflater: LayoutInflater,
    parent: ViewGroup? = null, attachToParent: Boolean = false
): T {

    val persistentClass : Class<T> = ( javaClass.genericSuperclass as ParameterizedType)
        .actualTypeArguments[0] as Class<T>

    val layoutName = persistentClass.simpleName.toSnakeCase().substringBeforeLast("_")

    val layoutResId = context.resources.getIdentifier(
        layoutName, "layout", context.packageName
    )

    return DataBindingUtil.inflate<T>(layoutInflater, layoutResId, parent, attachToParent)
}