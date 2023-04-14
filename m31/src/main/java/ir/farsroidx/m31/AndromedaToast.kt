package ir.farsroidx.m31

import android.annotation.SuppressLint
import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import ir.farsroidx.m31.additives.koinInjection

internal class AndromedaToast {

    enum class AlertMode {
        NORMAL, LIGHT, GRAY, DARK, PRIMARY, SUCCESS, INFO, WARNING, ERROR
    }

    private val applicationContext: Context by koinInjection()

    private val layoutInflater: LayoutInflater by koinInjection()

    private lateinit var layoutView: View

    private lateinit var cardView: CardView

    private lateinit var imageView: AppCompatImageView

    private lateinit var textView: AppCompatTextView

    private lateinit var toast: Toast

    fun initToast(
        message: CharSequence?,
        @DrawableRes icon: Int? = null,
        isLongDuration: Boolean = false,
        isRtlDirection: Boolean = false,
        alertMode: AlertMode = AlertMode.NORMAL
    ) {

        if (!this::layoutView.isInitialized) {
            @SuppressLint("InflateParams")
            layoutView = layoutInflater.inflate(
                R.layout.layout_toast, null, false
            )
        }

        if (!this::cardView.isInitialized) {
            cardView = layoutView.findViewById(R.id.cardView)
        }

        if (!this::imageView.isInitialized) {
            imageView = layoutView.findViewById(R.id.imageView)
        }

        if (!this::textView.isInitialized) {
            textView = layoutView.findViewById(R.id.textView)
        }

        layoutView.layoutDirection = if (isRtlDirection) {
            View.LAYOUT_DIRECTION_RTL
        } else View.LAYOUT_DIRECTION_LTR

        if (icon == null) {
            imageView.visibility = View.GONE
        } else {
            imageView.setImageResource( icon )
            imageView.visibility = View.VISIBLE
        }

        textView.text = message

        if (!this::toast.isInitialized) {
            toast = Toast( applicationContext ).apply {
                setGravity(
                    Gravity.BOTTOM or Gravity.CENTER_HORIZONTAL,
                    0, 100
                )
                @Suppress("DEPRECATION")
                view = layoutView
            }
        }

        toast.duration = if (isLongDuration) {
            Toast.LENGTH_LONG
        } else Toast.LENGTH_SHORT

        when( alertMode ) {
            AlertMode.NORMAL -> {
                cardView.setCardBackgroundColor(
                    ContextCompat.getColor(
                        applicationContext, R.color.m31_color_toast_normal_background
                    )
                )
                imageView.setColorFilter(
                    ContextCompat.getColor(
                        applicationContext, R.color.m31_color_toast_normal_text
                    ), android.graphics.PorterDuff.Mode.SRC_IN
                )
                textView.setTextColor(
                    ContextCompat.getColor(
                        applicationContext, R.color.m31_color_toast_normal_text
                    )
                )
            }
            AlertMode.LIGHT -> {
                cardView.setCardBackgroundColor(
                    ContextCompat.getColor(
                        applicationContext, R.color.m31_color_toast_light_background
                    )
                )
                imageView.setColorFilter(
                    ContextCompat.getColor(
                        applicationContext, R.color.m31_color_toast_light_text
                    ), android.graphics.PorterDuff.Mode.SRC_IN
                )
                textView.setTextColor(
                    ContextCompat.getColor(
                        applicationContext, R.color.m31_color_toast_light_text
                    )
                )
            }
            AlertMode.GRAY -> {
                cardView.setCardBackgroundColor(
                    ContextCompat.getColor(
                        applicationContext, R.color.m31_color_toast_gray_background
                    )
                )
                imageView.setColorFilter(
                    ContextCompat.getColor(
                        applicationContext, R.color.m31_color_toast_gray_text
                    ), android.graphics.PorterDuff.Mode.SRC_IN
                )
                textView.setTextColor(
                    ContextCompat.getColor(
                        applicationContext, R.color.m31_color_toast_gray_text
                    )
                )
            }
            AlertMode.DARK -> {
                cardView.setCardBackgroundColor(
                    ContextCompat.getColor(
                        applicationContext, R.color.m31_color_toast_dark_background
                    )
                )
                imageView.setColorFilter(
                    ContextCompat.getColor(
                        applicationContext, R.color.m31_color_toast_dark_text
                    ), android.graphics.PorterDuff.Mode.SRC_IN
                )
                textView.setTextColor(
                    ContextCompat.getColor(
                        applicationContext, R.color.m31_color_toast_dark_text
                    )
                )
            }
            AlertMode.PRIMARY -> {
                cardView.setCardBackgroundColor(
                    ContextCompat.getColor(
                        applicationContext, R.color.m31_color_toast_primary_background
                    )
                )
                imageView.setColorFilter(
                    ContextCompat.getColor(
                        applicationContext, R.color.m31_color_toast_primary_text
                    ), android.graphics.PorterDuff.Mode.SRC_IN
                )
                textView.setTextColor(
                    ContextCompat.getColor(
                        applicationContext, R.color.m31_color_toast_primary_text
                    )
                )
            }
            AlertMode.SUCCESS -> {
                imageView.setImageResource( R.drawable.m31_icon_done_all )
                cardView.setCardBackgroundColor(
                    ContextCompat.getColor(
                        applicationContext, R.color.m31_color_toast_success_background
                    )
                )
                imageView.setColorFilter(
                    ContextCompat.getColor(
                        applicationContext, R.color.m31_color_toast_success_text
                    ), android.graphics.PorterDuff.Mode.SRC_IN
                )
                textView.setTextColor(
                    ContextCompat.getColor(
                        applicationContext, R.color.m31_color_toast_success_text
                    )
                )
            }
            AlertMode.INFO -> {
                imageView.setImageResource( R.drawable.m31_icon_lightbulb )
                cardView.setCardBackgroundColor(
                    ContextCompat.getColor(
                        applicationContext, R.color.m31_color_toast_info_background
                    )
                )
                imageView.setColorFilter(
                    ContextCompat.getColor(
                        applicationContext, R.color.m31_color_toast_info_text
                    ), android.graphics.PorterDuff.Mode.SRC_IN
                )
                textView.setTextColor(
                    ContextCompat.getColor(
                        applicationContext, R.color.m31_color_toast_info_text
                    )
                )
            }
            AlertMode.WARNING -> {
                imageView.setImageResource( R.drawable.m31_icon_warning )
                cardView.setCardBackgroundColor(
                    ContextCompat.getColor(
                        applicationContext, R.color.m31_color_toast_warning_background
                    )
                )
                imageView.setColorFilter(
                    ContextCompat.getColor(
                        applicationContext, R.color.m31_color_toast_warning_text
                    ), android.graphics.PorterDuff.Mode.SRC_IN
                )
                textView.setTextColor(
                    ContextCompat.getColor(
                        applicationContext, R.color.m31_color_toast_warning_text
                    )
                )
            }
            AlertMode.ERROR -> {
                imageView.setImageResource( R.drawable.m31_icon_error )
                cardView.setCardBackgroundColor(
                    ContextCompat.getColor(
                        applicationContext, R.color.m31_color_toast_error_background
                    )
                )
                imageView.setColorFilter(
                    ContextCompat.getColor(
                        applicationContext, R.color.m31_color_toast_error_text
                    ), android.graphics.PorterDuff.Mode.SRC_IN
                )
                textView.setTextColor(
                    ContextCompat.getColor(
                        applicationContext, R.color.m31_color_toast_error_text
                    )
                )
            }
        }

        toast.show()
    }
}