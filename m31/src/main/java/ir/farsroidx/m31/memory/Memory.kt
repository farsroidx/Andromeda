package ir.farsroidx.m31.memory

interface Memory {

    suspend fun <T: Any> store(key: String, value: T)

    suspend fun <T: Any> getByNull(key: String): T?

    suspend fun <T: Any> get(key: String): T

    suspend fun <T: Any> get(key: String, alternate: T?): T

    suspend fun isKeyStored(key: String): Boolean

    suspend fun remove(vararg keys: String)

    suspend fun clear()

}