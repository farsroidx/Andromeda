@file:Suppress("unused")

package ir.farsroidx.m31.additives

import android.content.Context
import android.content.DialogInterface
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import androidx.annotation.DrawableRes
import androidx.annotation.StyleRes
import androidx.appcompat.app.AlertDialog
import ir.farsroidx.m31.R

// TODO: Dialog ========================================================================= Dialog ===

fun Context.dialogSingleButton(
    title:   CharSequence,
    message: CharSequence,
    action: CharSequence,
    onClick: DialogInterface.OnClickListener?,
    cancelable: Boolean = false,
    @DrawableRes iconResId: Int? = null,
    @StyleRes style: Int = R.style.Theme_Andromeda_Dialog,
) = showAlertDialog(
    title, message,
    action, null, null,
    onClick, null, null,
    cancelable, iconResId, style
)

fun Context.dialogTwoButton(
    title:   CharSequence,
    message: CharSequence,
    positiveAction: CharSequence,
    negativeAction: CharSequence,
    onPositiveClick: DialogInterface.OnClickListener?,
    onNegativeClick: DialogInterface.OnClickListener?,
    cancelable: Boolean = false,
    @DrawableRes iconResId: Int? = null,
    @StyleRes style: Int = R.style.Theme_Andromeda_Dialog,
) = showAlertDialog(
    title, message,
    positiveAction, negativeAction, null,
    onPositiveClick, onNegativeClick, null,
    cancelable, iconResId, style
)

fun Context.dialogThreeButton(
    title:   CharSequence,
    message: CharSequence,
    positiveAction: CharSequence,
    negativeAction: CharSequence,
    neutralAction:  CharSequence,
    onPositiveClick: DialogInterface.OnClickListener?,
    onNegativeClick: DialogInterface.OnClickListener?,
    onNeutralClick:  DialogInterface.OnClickListener?,
    cancelable: Boolean = false,
    @DrawableRes iconResId: Int? = null,
    @StyleRes style: Int = R.style.Theme_Andromeda_Dialog,
) = showAlertDialog(
    title, message,
    positiveAction, negativeAction, neutralAction,
    onPositiveClick, onNegativeClick, onNeutralClick,
    cancelable, iconResId, style
)

private var mAlertDialog: AlertDialog? = null

private fun Context.showAlertDialog(
    title: CharSequence?, message: CharSequence?,
    positiveAction: CharSequence?, negativeAction: CharSequence?, neutralAction: CharSequence?,
    onPositiveClick: DialogInterface.OnClickListener?,
    onNegativeClick: DialogInterface.OnClickListener?,
    onNeutralClick:  DialogInterface.OnClickListener?,
    cancelable: Boolean, @DrawableRes iconId: Int?, @StyleRes style: Int,
) {
    mAlertDialog?.let { if (it.isShowing) { it.dismiss() } }

    mAlertDialog = AlertDialog.Builder(this, style).create()

    mAlertDialog?.apply {

        setCancelable( cancelable )

        iconId?.let { setIcon( it ) }

        title?.let { setTitle( it ) }

        message?.let { setMessage( it ) }

        positiveAction?.let {
            setButton(AlertDialog.BUTTON_POSITIVE, positiveAction, onPositiveClick)
            getButton(AlertDialog.BUTTON_POSITIVE)?.isEnabled = true
        } ?: run {
            getButton(AlertDialog.BUTTON_POSITIVE)?.isEnabled = false
        }

        negativeAction?.let {
            setButton(AlertDialog.BUTTON_NEGATIVE, negativeAction, onNegativeClick)
            getButton(AlertDialog.BUTTON_NEGATIVE)?.isEnabled = true
        } ?: run {
            getButton(AlertDialog.BUTTON_NEGATIVE)?.isEnabled = false
        }

        neutralAction?.let {
            setButton(AlertDialog.BUTTON_NEUTRAL, neutralAction, onNeutralClick)
            getButton(AlertDialog.BUTTON_NEUTRAL)?.isEnabled = true
        } ?: run {
            getButton(AlertDialog.BUTTON_NEUTRAL)?.isEnabled = false
        }

        show()

        withCenteredButtons()
    }
}

private fun AlertDialog.withCenteredButtons() {

    val positive = getButton(AlertDialog.BUTTON_POSITIVE)
    val neutral  = getButton(AlertDialog.BUTTON_NEUTRAL)
    val negative = getButton(AlertDialog.BUTTON_NEGATIVE)

    // Disable the material spacer view in case there is one
    val parent = positive.parent as? LinearLayout
    parent?.gravity = Gravity.CENTER_HORIZONTAL

    val leftSpacer = parent?.getChildAt(1)
    leftSpacer?.visibility = View.GONE

    val layoutParams = LinearLayout.LayoutParams(
        LinearLayout.LayoutParams.MATCH_PARENT,
        LinearLayout.LayoutParams.WRAP_CONTENT
    )

    layoutParams.weight  = 1f
    layoutParams.gravity = Gravity.CENTER

    positive.layoutParams = layoutParams
    neutral.layoutParams  = layoutParams
    negative.layoutParams = layoutParams
}