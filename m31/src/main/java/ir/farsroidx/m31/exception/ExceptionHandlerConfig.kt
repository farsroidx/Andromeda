package ir.farsroidx.m31.exception

import androidx.annotation.Size
import ir.farsroidx.m31.AndromedaConfig
import ir.farsroidx.m31.additives.validationEmail

class ExceptionHandlerConfig : AndromedaConfig {

    internal var emailAddress: String? = null

    fun setDeveloperEmail(@Size(min = 1) emailAddress: String) {
        validationEmail(emailAddress)
        this.emailAddress = emailAddress
    }

}