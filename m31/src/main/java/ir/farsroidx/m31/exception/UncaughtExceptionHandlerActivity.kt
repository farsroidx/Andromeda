@file:Suppress("UNUSED_PARAMETER")

package ir.farsroidx.m31.exception

import android.app.ApplicationErrorReport
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Layout
import android.text.method.LinkMovementMethod
import android.text.style.AlignmentSpan
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ShareCompat
import androidx.core.text.*
import ir.farsroidx.m31.R
import ir.farsroidx.m31.additives.appendByLine
import ir.farsroidx.m31.databinding.ActivityUncaughtExceptionHandlerBinding

class UncaughtExceptionHandlerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUncaughtExceptionHandlerBinding

    private var userEmailReceiver = ""
    private var lastExceptionHtml = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityUncaughtExceptionHandlerBinding.inflate(layoutInflater)
            .apply {
                setContentView(root)
            }

        intent.getParcelableExtra<ApplicationErrorReport>(EXTRA_THROWABLE)
            .apply {
                if (this != null) {
                    try {
                        val config = intent.getSerializableExtra(EXTRA_CONFIG) as
                            ExceptionHandlerConfig
                        if (config.emailAddress != null) {
                            showError(this, config)
                        } else {
                            Toast.makeText(
                                this@UncaughtExceptionHandlerActivity,
                                "Developer Email not configuration.", Toast.LENGTH_LONG
                            ).show()
                            finish()
                        }
                    } catch (throwable: Throwable) {
                        showReportActivity(throwable)
                    }
                } else {
                    showDefault()
                }
            }
    }

    private fun showError(
        report: ApplicationErrorReport,
        config: ExceptionHandlerConfig
    ) {

        userEmailReceiver = config.emailAddress ?: ""

        val html = StringBuilder()

        binding.crashTextView.apply {

            movementMethod = LinkMovementMethod.getInstance()

            val builder = buildSpannedString {

                inSpans(
                    AlignmentSpan.Standard(Layout.Alignment.ALIGN_CENTER)
                ) {
                    color(Color.parseColor("#ff5c33")) {
                        bold {
                            scale(1.1f) {
                                appendByLine(report.crashInfo.exceptionMessage, 2)
                            }
                        }
                    }
                }

                inSpans(
                    AlignmentSpan.Standard(Layout.Alignment.ALIGN_CENTER)
                ) {
                    color(Color.parseColor("#666666")) {
                        bold {
                            scale(0.8f) {
                                appendByLine(report.crashInfo.exceptionClassName)
                            }
                        }
                    }
                }

                inSpans(
                    AlignmentSpan.Standard(Layout.Alignment.ALIGN_CENTER)
                ) {
                    scale(0.9f) {
                        color(Color.parseColor("#3399ff")) {
                            bold {
                                append(
                                    report.crashInfo.throwClassName + ": "
                                )
                                scale(1.5f) {
                                    underline {
                                        appendByLine(report.crashInfo.throwLineNumber.toString())
                                    }
                                }
                            }
                        }
                    }
                }

////                appendByLine(report.crashInfo.throwFileName)
//                appendByLine(report.crashInfo.throwMethodName, 2)
////                appendByLine(report.crashInfo.stackTrace)
//                scale(0.5f) {
//                    appendByLine("Text at half size")
//                }
//                backgroundColor(Color.LTGRAY) {
//                    appendByLine("Background LightGRAY")
//                }
//                bold {
//                    underline {
//                        appendByLine("bold and underlined")
//                    }
//                }
//                italic {
//                    underline {
//                        appendByLine("italic and underlined")
//                    }
//                }
//                bold {
//                    italic {
//                        appendByLine("bold and italic")
//                    }
//                }
//                strikeThrough {
//                    append("strikeThrough")
//                }
//                superscript {
//                    appendByLine("2f")
//                }
//                inSpans(
//                    object : ClickableSpan() {
//                        override fun onClick(view: View) {
//                            Toast.makeText(this@UncaughtExceptionHandlerActivity, "test", Toast.LENGTH_SHORT).show()
//                        }
//                    }
//                ) {
//                    color(Color.BLUE) {
//                        bold {
//                            appendByLine("@MinooGroup")
//                        }
//                    }
//                }
            }

            text = builder

            html.append(builder.toHtml())
        }

        html.append( "<h2>" + getString(R.string.m31_stack_trace) + "</h2>" )

        binding.traceTextView.apply {

            val builder = buildSpannedString {
                append(report.crashInfo.stackTrace.trim())
            }

            text = builder

            html.append(builder.toHtml())
        }

        lastExceptionHtml = html.toString()
    }

    private fun showDefault() {

    }

    private fun showReportActivity(throwable: Throwable) {
        ApplicationErrorReport().apply {
            installerPackageName = packageManager.getInstallerPackageName(
                this@UncaughtExceptionHandlerActivity.packageName
            )
            crashInfo   = ApplicationErrorReport.CrashInfo(throwable)
            type        = ApplicationErrorReport.TYPE_CRASH
            time        = System.currentTimeMillis()
            packageName = this@UncaughtExceptionHandlerActivity.packageName
            processName = this@UncaughtExceptionHandlerActivity.packageName
            systemApp   = false

            startActivity(
                Intent(Intent.ACTION_APP_ERROR)
                    .setPackage("com.google.android.feedback")
                    .putExtra(Intent.EXTRA_BUG_REPORT, this)
                    .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            )
        }
    }

    fun onSendPressed(view: View) {

        Toast.makeText(this, "please wait ...", Toast.LENGTH_SHORT).show()

        val mailId = "yourmail@gmail.com"

        try {
            ShareCompat.IntentBuilder.from(this)
                .setType("message/rfc822")
                .addEmailTo(mailId)
                .setSubject("The Subject")
                .setHtmlText(lastExceptionHtml)
                .startChooser()
        } catch (e: Exception) {
            Toast.makeText(
                baseContext, "Have not email application!", Toast.LENGTH_SHORT
            ).show()
        }
    }

    fun onBackPressed(view: View) {
//        onBackPressed()
        finish()
    }

//    override fun onBackPressed() {
//        super.onBackPressed()
//        finish()
//    }

    companion object {
        const val EXTRA_THROWABLE = "extraThrowable"
        const val EXTRA_CONFIG    = "extraConfig"
    }
}