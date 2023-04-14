package ir.farsroidx.m31.additives

import androidx.annotation.DrawableRes
import ir.farsroidx.m31.AndromedaToast

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