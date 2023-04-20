@file:Suppress("unused")

package ir.farsroidx.m31.additives

import android.content.Context
import android.graphics.Color
import android.view.View
import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import ir.farsroidx.m31.AndromedaActivity
import ir.farsroidx.m31.AndromedaToast
import ir.farsroidx.m31.R

// TODO: Notifier ===================================================================== Notifier ===

fun Context.defToast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration).show()
}

fun Fragment.defToast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(requireContext(), message, duration).show()
}

fun Context.defLongToast(message: String) = defToast(message, Toast.LENGTH_LONG)

fun Fragment.defLongToast(message: String) = defToast(message, Toast.LENGTH_LONG)

fun toast(
    message: CharSequence?,
    @DrawableRes icon: Int? = null,
    isLongDuration: Boolean = false,
    isRtlDirection: Boolean = false
) = initializeToast(
    message, icon, isLongDuration, isRtlDirection, AndromedaToast.AlertMode.NORMAL
)

fun lightToast(
    message: CharSequence?,
    @DrawableRes icon: Int? = null,
    isLongDuration: Boolean = false,
    isRtlDirection: Boolean = false
) = initializeToast(
    message, icon, isLongDuration, isRtlDirection, AndromedaToast.AlertMode.LIGHT
)

fun grayToast(
    message: CharSequence?,
    @DrawableRes icon: Int? = null,
    isLongDuration: Boolean = false,
    isRtlDirection: Boolean = false
) = initializeToast(
    message, icon, isLongDuration, isRtlDirection, AndromedaToast.AlertMode.GRAY
)

fun darkToast(
    message: CharSequence?,
    @DrawableRes icon: Int? = null,
    isLongDuration: Boolean = false,
    isRtlDirection: Boolean = false
) = initializeToast(
    message, icon, isLongDuration, isRtlDirection, AndromedaToast.AlertMode.DARK
)

fun primaryToast(
    message: CharSequence?,
    @DrawableRes icon: Int? = null,
    isLongDuration: Boolean = false,
    isRtlDirection: Boolean = false
) = initializeToast(
    message, icon, isLongDuration, isRtlDirection, AndromedaToast.AlertMode.PRIMARY
)

fun successToast(
    message: CharSequence?,
    isLongDuration: Boolean = false,
    isRtlDirection: Boolean = false
) = initializeToast(
    message, null, isLongDuration, isRtlDirection, AndromedaToast.AlertMode.SUCCESS
)

fun infoToast(
    message: CharSequence?,
    isLongDuration: Boolean = false,
    isRtlDirection: Boolean = false
) = initializeToast(
    message, null, isLongDuration, isRtlDirection, AndromedaToast.AlertMode.INFO
)

fun warningToast(
    message: CharSequence?,
    isLongDuration: Boolean = false,
    isRtlDirection: Boolean = false
) = initializeToast(
    message, null, isLongDuration, isRtlDirection, AndromedaToast.AlertMode.WARNING
)

fun errorToast(
    message: CharSequence?,
    isLongDuration: Boolean = false,
    isRtlDirection: Boolean = false
) = initializeToast(
    message, null, isLongDuration, isRtlDirection, AndromedaToast.AlertMode.ERROR
)

private fun initializeToast(
    message: CharSequence?, @DrawableRes icon: Int?,
    isLongDuration: Boolean, isRtlDirection: Boolean,
    alertMode: AndromedaToast.AlertMode
) {
    val andromedaToast: AndromedaToast by koinInjection()
    andromedaToast.initToast(
        message, icon, isLongDuration, isRtlDirection, alertMode
    )
}

// TODO: Snackbar ===================================================================== Snackbar ===

fun AndromedaActivity<*>.lightSnackbar(
    message: CharSequence,
    duration: Int = Snackbar.LENGTH_LONG,
    animation: Int = Snackbar.ANIMATION_MODE_SLIDE,
    action: String? = null,
    clickListener: View.OnClickListener? = null,
) = initializeSnackbar(1, message, duration, animation, action, clickListener)

fun AndromedaActivity<*>.graySnackbar(
    message: CharSequence,
    duration: Int = Snackbar.LENGTH_LONG,
    animation: Int = Snackbar.ANIMATION_MODE_SLIDE,
    action: String? = null,
    clickListener: View.OnClickListener? = null,
) = initializeSnackbar(2, message, duration, animation, action, clickListener)

fun AndromedaActivity<*>.darkSnackbar(
    message: CharSequence,
    duration: Int = Snackbar.LENGTH_LONG,
    animation: Int = Snackbar.ANIMATION_MODE_SLIDE,
    action: String? = null,
    clickListener: View.OnClickListener? = null,
) = initializeSnackbar(3, message, duration, animation, action, clickListener)

fun AndromedaActivity<*>.infoSnackbar(
    message: CharSequence,
    duration: Int = Snackbar.LENGTH_LONG,
    animation: Int = Snackbar.ANIMATION_MODE_SLIDE,
    action: String? = null,
    clickListener: View.OnClickListener? = null,
) = initializeSnackbar(4, message, duration, animation, action, clickListener)

fun AndromedaActivity<*>.successSnackbar(
    message: CharSequence,
    duration: Int = Snackbar.LENGTH_LONG,
    animation: Int = Snackbar.ANIMATION_MODE_SLIDE,
    action: String? = null,
    clickListener: View.OnClickListener? = null,
) = initializeSnackbar(5, message, duration, animation, action, clickListener)

fun AndromedaActivity<*>.warningSnackbar(
    message: CharSequence,
    duration: Int = Snackbar.LENGTH_LONG,
    animation: Int = Snackbar.ANIMATION_MODE_SLIDE,
    action: String? = null,
    clickListener: View.OnClickListener? = null,
) = initializeSnackbar(6, message, duration, animation, action, clickListener)

fun AndromedaActivity<*>.showSnackbarError(
    message: CharSequence,
    duration: Int = Snackbar.LENGTH_LONG,
    animation: Int = Snackbar.ANIMATION_MODE_SLIDE,
    action: String? = null,
    clickListener: View.OnClickListener? = null,
) = initializeSnackbar(7, message, duration, animation, action, clickListener)

private fun AndromedaActivity<*>.initializeSnackbar(
    state: Int,
    message: CharSequence,
    duration: Int,
    animation: Int,
    action: String?,
    clickListener: View.OnClickListener?,
) = Snackbar.make(
    this, this.findViewById(android.R.id.content), message, duration
).apply {

    animationMode = animation

    action?.let {
        setAction(it, clickListener)
    } ?: run {
        if (duration != Snackbar.LENGTH_SHORT && duration != Snackbar.LENGTH_LONG) {
            setDuration(Snackbar.LENGTH_LONG)
        }
    }

    when(state) {
        1 -> {
            setBackgroundTint(getColorResource(R.color.m31_black))
            setTextColor(getColorResource(R.color.m31_black))
            setActionTextColor(getColorResource(R.color.pink_500))
        }
        2 -> {
            setBackgroundTint(getColorResource(R.color.gray500))
            setTextColor(getColorResource(R.color.gray900))
            setActionTextColor(getColorResource(R.color.m31_white))
        }
        3 -> {
            setBackgroundTint(getColorResource(R.color.m31_black))
            setTextColor(getColorResource(R.color.m31_white))
            setActionTextColor(getColorResource(R.color.pink_500))
        }
        4 -> {
            setBackgroundTint(getColorResource(R.color.light_blue_400))
            setTextColor(getColorResource(R.color.light_blue_900))
            setActionTextColor(getColorResource(R.color.m31_white))
        }
        5 -> {
            setBackgroundTint(getColorResource(R.color.light_green_400))
            setTextColor(getColorResource(R.color.light_green_900))
            setActionTextColor(getColorResource(R.color.m31_white))
        }
        6 -> {
            setBackgroundTint(getColorResource(R.color.orange_600))
            setTextColor(getColorResource(R.color.m31_black))
            setActionTextColor(getColorResource(R.color.m31_white))
        }
        7 -> {
            setBackgroundTint(getColorResource(R.color.red_400))
            setTextColor(getColorResource(R.color.m31_black))
            setActionTextColor(getColorResource(R.color.m31_white))
        }
    }

}.show()


fun AndromedaActivity<*>.showColoredSnackbar(
    message: CharSequence,
    messageColor: Int = Color.BLACK,
    action: String? = null,
    actionColor: Int = Color.BLUE,
    backgroundColor: Int = Color.WHITE,
    duration: Int = Snackbar.LENGTH_LONG,
    animation: Int = Snackbar.ANIMATION_MODE_SLIDE,
    clickListener: View.OnClickListener? = null,
) = Snackbar.make(
    this, this.findViewById(android.R.id.content), message, duration
).apply {

    animationMode = animation

    setDuration(duration)
    setTextColor(messageColor)
    setActionTextColor(actionColor)
    setBackgroundTint(backgroundColor)

    action?.let {
        setAction(it, clickListener)
    } ?: run {
        if (duration == Snackbar.LENGTH_INDEFINITE) {
            setDuration(Snackbar.LENGTH_LONG)
        }
    }

}.show()