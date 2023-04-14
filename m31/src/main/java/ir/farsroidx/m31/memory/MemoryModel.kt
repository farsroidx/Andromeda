package ir.farsroidx.m31.memory

internal data class MemoryModel(
    val value: Any,
    val expirationTime: Long? = null
)
