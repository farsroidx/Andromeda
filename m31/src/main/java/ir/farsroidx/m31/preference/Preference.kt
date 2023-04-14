package ir.farsroidx.m31.preference

interface Preference {

    suspend fun store(key: String, value: String)

    suspend fun store(key: String, value: Int)

    suspend fun store(key: String, value: Float)

    suspend fun store(key: String, value: Double)

    suspend fun store(key: String, value: Long)

    suspend fun store(key: String, value: Boolean)

    suspend fun store(key: String, value: Set<String>)

    suspend fun get(key: String, alternate: String): String

    suspend fun get(key: String, alternate: Int): Int

    suspend fun get(key: String, alternate: Float): Float

    suspend fun get(key: String, alternate: Double): Double

    suspend fun get(key: String, alternate: Long): Long

    suspend fun get(key: String, alternate: Boolean): Boolean

    suspend fun get(key: String, alternate: Set<String>): Set<String>

    suspend fun isKeyStored(key: String): Boolean

    suspend fun remove(vararg keys: String)

    suspend fun clear()

}