@file:Suppress("unused")

package ir.farsroidx.m31

class AndromedaException : RuntimeException {

    constructor(message: String)
        : super(message)

    constructor(cause: Throwable)
        : super(cause)

    constructor(message: String, cause: Throwable)
        : super(message, cause)

}