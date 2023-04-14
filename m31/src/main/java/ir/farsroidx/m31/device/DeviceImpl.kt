package ir.farsroidx.m31.device

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager
import android.provider.Settings
import androidx.annotation.RequiresPermission

@SuppressLint("HardwareIds")
internal class DeviceImpl(
    private val context: Context,
    private val config: DeviceConfig,
) : Device {

    private val androidDeviceId by lazy {
        Settings.Secure.getString(
            context.applicationContext.contentResolver, Settings.Secure.ANDROID_ID
        )
    }

    override fun getDeviceId(): String = androidDeviceId

    @RequiresPermission(Manifest.permission.VIBRATE)
    override fun vibrateDevice(milliseconds: Long) {

        val vibrator = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            val vibratorManager = context.getSystemService(Context.VIBRATOR_MANAGER_SERVICE)
                    as VibratorManager
            vibratorManager.defaultVibrator
        } else {
            @Suppress("DEPRECATION")
            context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            vibrator.vibrate(
                VibrationEffect.createOneShot(
                    milliseconds, VibrationEffect.DEFAULT_AMPLITUDE
                )
            )
        } else {
            vibrator.vibrate(milliseconds)
        }
    }

    override fun getSdkVersion(): Int = Build.VERSION.SDK_INT

    override fun getSdkCodeName(): String = Build.VERSION.CODENAME

    override fun getSdkIncremental(): String = Build.VERSION.INCREMENTAL

    override fun getSdkRelease(): String = Build.VERSION.RELEASE
}