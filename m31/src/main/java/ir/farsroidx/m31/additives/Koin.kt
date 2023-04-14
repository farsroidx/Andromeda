@file:Suppress("REDUNDANT_ELSE_IN_WHEN", "unused")

package ir.farsroidx.m31.additives

import android.content.Context
import android.os.Debug
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import com.google.gson.Gson
import ir.farsroidx.m31.*
import ir.farsroidx.m31.app.App
import ir.farsroidx.m31.app.AppConfig
import ir.farsroidx.m31.app.AppImpl
import ir.farsroidx.m31.device.Device
import ir.farsroidx.m31.device.DeviceConfig
import ir.farsroidx.m31.device.DeviceImpl
import ir.farsroidx.m31.dispatcher.Dispatcher
import ir.farsroidx.m31.dispatcher.DispatcherImpl
import ir.farsroidx.m31.exception.ExceptionHandlerConfig
import ir.farsroidx.m31.exception.UncaughtExceptionHandlerImpl
import ir.farsroidx.m31.memory.Memory
import ir.farsroidx.m31.memory.MemoryConfig
import ir.farsroidx.m31.memory.MemoryImpl
import ir.farsroidx.m31.preference.Preference
import ir.farsroidx.m31.preference.PreferenceConfig
import ir.farsroidx.m31.preference.PreferenceImpl
import ir.farsroidx.m31.utils.Utils
import ir.farsroidx.m31.utils.UtilsConfig
import ir.farsroidx.m31.utils.UtilsImpl
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.core.qualifier.Qualifier
import org.koin.dsl.module
import org.koin.java.KoinJavaComponent

// TODO: Koin ================================================================================== ///

internal inline fun <reified T> koinInjection(
    qualifier: Qualifier? = null,
): Lazy<T> {
    return lazy( LazyThreadSafetyMode.SYNCHRONIZED ) {
        KoinJavaComponent.get(
            T::class.java, qualifier, null
        )
    }
}

internal fun initializedKoin(
    application: AndromedaApplication, configs: List<AndromedaConfig>
) = startKoin {
    androidLogger(Level.NONE)
    androidContext(application)
    mutableListOf( commonModule() )
        .apply {
            configs.forEach { config ->
                when (config) {
                    is ExceptionHandlerConfig -> exceptionModule(application, config)
                    is AppConfig              -> add( appModule()               )
                    is DeviceConfig           -> add( deviceModule( config )    )
                    is MemoryConfig           -> add( memoryModule( config)     )
                    is PreferenceConfig       -> add( preferenceModule( config) )
                    is UtilsConfig            -> add( utilsModule( config )     )
                    else -> {
                        throw AndromedaException(
                            "This type of AndromedaConfig is not supported, this config is invalid."
                        )
                    }
                }
            }
            modules(this)
        }
}

private fun commonModule() = module {
    single { Gson() }
    single<Dispatcher> {
        DispatcherImpl()
    }
    single {
        Handler( Looper.getMainLooper() )
    }
    single {
        LayoutInflater.from( androidContext() )
    }
    single {
        AndromedaToast()
    }
}

private fun exceptionModule(context: Context, config: ExceptionHandlerConfig) {
    if ( !Debug.waitingForDebugger() && !Debug.isDebuggerConnected() ) {
        Thread.setDefaultUncaughtExceptionHandler(
            UncaughtExceptionHandlerImpl(
                context, config
            )
        )
    }
}

private fun appModule() = module {
    single<App> {
        AppImpl( androidContext() )
    }
    AndromedaState.isAppUnitInitialized = true
}

private fun deviceModule(config: DeviceConfig) = module {
    single<Device> {
        DeviceImpl( androidContext(), config )
    }
    AndromedaState.isDeviceUnitInitialized = true
}

private fun memoryModule(config: MemoryConfig) = module {
    single<Memory> {
        MemoryImpl( config )
    }
    AndromedaState.isMemoryUnitInitialized = true
}

private fun preferenceModule(config: PreferenceConfig) = module {
    single<Preference> {
        PreferenceImpl(
            androidContext(), get(), config
        )
    }
    AndromedaState.isPreferenceUnitInitialized = true
}

private fun utilsModule(config: UtilsConfig) = module {
    single<Utils> {
        UtilsImpl( androidContext(), config )
    }
    AndromedaState.isUtilsUnitInitialized = true
}