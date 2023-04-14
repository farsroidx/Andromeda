package ir.farsroidx.m31.device

import android.Manifest
import androidx.annotation.RequiresPermission

interface Device {

    @RequiresPermission(
        Manifest.permission.READ_PHONE_STATE
    )
    fun getDeviceId(): String

    @RequiresPermission(
        Manifest.permission.VIBRATE
    )
    fun vibrateDevice(milliseconds: Long)

    fun getSdkVersion(): Int

    fun getSdkCodeName(): String

    fun getSdkIncremental(): String

    fun getSdkRelease(): String
}