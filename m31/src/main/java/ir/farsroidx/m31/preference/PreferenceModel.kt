package ir.farsroidx.m31.preference

internal class PreferenceModel <T> (
    val value: T,
    val expirationTime: Long? = null
)