package ir.farsroidx.m31.exception

import android.app.ApplicationErrorReport
import android.content.Context
import android.content.Intent
import ir.farsroidx.m31.additives.killApplication

internal class UncaughtExceptionHandlerImpl(
    private val context: Context, private val config: ExceptionHandlerConfig
) : Thread.UncaughtExceptionHandler {

    private val defaultHandler = Thread.getDefaultUncaughtExceptionHandler()

    override fun uncaughtException(thread: Thread, throwable: Throwable) {
        try {
            context.startActivity(
                Intent(
                    context.applicationContext, UncaughtExceptionHandlerActivity::class.java
                ).setFlags(
                    Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                ).putExtra(
                    UncaughtExceptionHandlerActivity.EXTRA_THROWABLE, getCrashDetail(throwable)
                ).putExtra(
                    UncaughtExceptionHandlerActivity.EXTRA_CONFIG, config
                )
            )
        } catch (throwable: Throwable) {
            defaultHandler?.uncaughtException(thread, throwable)
        } finally {
            killApplication()
        }
    }

    private fun getCrashDetail(throwable: Throwable): ApplicationErrorReport {
        return ApplicationErrorReport().apply {
            installerPackageName = context.packageManager.getInstallerPackageName(context.packageName)
            crashInfo = ApplicationErrorReport.CrashInfo(throwable)
            packageName = context.packageName
            processName = context.packageName
            type = ApplicationErrorReport.TYPE_CRASH
            time = System.currentTimeMillis()
            systemApp = false
        }
    }

//    val takeScreenshot: Bitmap?
//        get() {
//            val activity = mForegroundInstance ?: return null
//            val window = activity.window ?: return null
//            val view = window.decorView
//
//            view.buildDrawingCache()
//
//            val cache = view.drawingCache
//
//            val screenshot = cache!!.copy(cache.config, false)
//
//            if (!view.isDrawingCacheEnabled) {
//                view.destroyDrawingCache()
//            }
//
//            return screenshot
//        }
//
//    fun saveScreenShot(bitmap: Bitmap): String? {
//        var stream: FileOutputStream? = null
//        try {
//            val temp = File.createTempFile("crash-report", ".jpg")
//            stream = FileOutputStream(temp)
//            bitmap.compress(Bitmap.CompressFormat.JPEG, 80, stream)
//            return temp.absolutePath
//        } catch (e: IOException) {
//            Log.e(TAG, e.message, e)
//        } finally {
//            if (stream != null) {
//                try {
//                    stream.close()
//                } catch (e: IOException) {
//                    Log.e(
//                        TAG,
//                        e.message,
//                        e
//                    )
//                }
//            }
//        }
//        return null
//    }

}