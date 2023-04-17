package ir.farsroidx.m31.memory

import ir.farsroidx.m31.AndromedaException
import ir.farsroidx.m31.additives.isExpired
import ir.farsroidx.m31.additives.toExpirationTime

internal class MemoryImpl(
    private val config: MemoryConfig
) : Memory {

    private val runtimeMemory = mutableMapOf<String, MemoryModel>()

    override fun <T: Any> store(key: String, value: T) {
        runtimeMemory[key] = MemoryModel(
            value, config.expirationTime.toExpirationTime(
                config.expirationUnit
            )
        )
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T: Any> getByNull(key: String): T? {
        return if (runtimeMemory.containsKey(key)) {
            val valueObject = runtimeMemory[key]!!
            if (valueObject.expirationTime.isExpired()) {
                null
            } else ( valueObject.value as T )
        } else {
            null
        }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T: Any> get(key: String): T {
        if (runtimeMemory.containsKey(key)) {
            return ( runtimeMemory[key]!!.value as T )
        } else {
            throw AndromedaException(
                "There is no such key named $key in the memory."
            )
        }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T: Any> get(key: String, alternate: T?): T {

        if (runtimeMemory.containsKey(key)) {
            return ( runtimeMemory[key]!!.value as T )
        }

        if (alternate == null)
            throw AndromedaException(
                "There is no such key named $key in the memory."
            )

        return alternate
    }

    override fun isKeyStored(key: String): Boolean {
        return if (runtimeMemory.containsKey(key)) {
            if (runtimeMemory[key]?.expirationTime.isExpired()) {
                runtimeMemory.remove(key)
                false
            } else true
        } else false
    }

    override fun remove(vararg keys: String) {
        keys.forEach {
            if (runtimeMemory.containsKey(it)) {
                runtimeMemory.remove(
                    it
                )
            }
        }
    }

    override fun clear() = runtimeMemory.clear()
}