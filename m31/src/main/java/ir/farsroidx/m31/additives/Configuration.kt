package ir.farsroidx.m31.additives

import ir.farsroidx.m31.app.AppConfig
import ir.farsroidx.m31.device.DeviceConfig
import ir.farsroidx.m31.exception.ExceptionHandlerConfig
import ir.farsroidx.m31.memory.MemoryConfig
import ir.farsroidx.m31.preference.PreferenceConfig
import ir.farsroidx.m31.utils.UtilsConfig

// TODO: Configuration ========================================================================= ///

fun configAppModule(
    invoker: AppConfig.() -> Unit = {}
): AppConfig {
    return AppConfig()
        .apply(invoker)
}

fun configDeviceModule(
    invoker: DeviceConfig.() -> Unit = {}
): DeviceConfig {
    return DeviceConfig()
        .apply(invoker)
}

fun configExceptionHandlerModule(
    invoker: ExceptionHandlerConfig.() -> Unit = {}
): ExceptionHandlerConfig {
    return ExceptionHandlerConfig()
        .apply(invoker)
}

fun configMemoryModule(
    invoker: MemoryConfig.() -> Unit = {}
): MemoryConfig {
    return MemoryConfig()
        .apply(invoker)
}

fun configPreferenceModule(
    invoker: PreferenceConfig.() -> Unit = {}
): PreferenceConfig {
    return PreferenceConfig()
        .apply(invoker)
}

fun configUtilsModule(
    invoker: UtilsConfig.() -> Unit = {}
): UtilsConfig {
    return UtilsConfig()
        .apply(invoker)
}