package ir.farsroidx.m31.memory

import ir.farsroidx.m31.AndromedaConfig
import ir.farsroidx.m31.AndromedaException
import ir.farsroidx.m31.AndromedaTimeUnit

class MemoryConfig : AndromedaConfig {

    internal var expirationTime: Int? = null
    internal var expirationUnit: AndromedaTimeUnit? = null

    fun setExpirationTime(time: Int, unit: AndromedaTimeUnit = AndromedaTimeUnit.Minutes) {
        if (time < 1) {
            throw AndromedaException("Time cannot be less than [1].")
        }
        if (unit is AndromedaTimeUnit.Seconds) {
            if (time < 15) {
                throw AndromedaException("Time cannot be less than 15 seconds [minimum = 15s].")
            }
        }
        this.expirationTime = time
        this.expirationUnit = unit
    }
}