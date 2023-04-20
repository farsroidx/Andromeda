@file:Suppress("REDUNDANT_ELSE_IN_WHEN", "unused")

package ir.farsroidx.m31.additives

import android.content.Context
import android.os.Debug
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import com.google.gson.Gson
import ir.farsroidx.m31.AndromedaApplication
import ir.farsroidx.m31.AndromedaConfig
import ir.farsroidx.m31.AndromedaException
import ir.farsroidx.m31.AndromedaToast
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
import ir.farsroidx.m31.manager.Manager
import ir.farsroidx.m31.manager.ManagerConfig
import ir.farsroidx.m31.manager.ManagerImpl
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

// TODO: Koin ============================================================================= Koin ===

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
                    is AppConfig              -> add( appModule()                )
                    is DeviceConfig           -> add( deviceModule( config )     )
                    is ManagerConfig          -> add( managerModule( config )    )
                    is MemoryConfig           -> add( memoryModule( config )     )
                    is PreferenceConfig       -> add( preferenceModule( config ) )
                    is UtilsConfig            -> add( utilsModule( config )      )
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
}

private fun deviceModule(config: DeviceConfig) = module {
    single<Device> {
        DeviceImpl( androidContext(), config )
    }
}

private fun managerModule(config: ManagerConfig) = module {
    single<Manager> {
        ManagerImpl( androidContext(), config )
    }
}

private fun memoryModule(config: MemoryConfig) = module {
    single<Memory> {
        MemoryImpl( config )
    }
}

private fun preferenceModule(config: PreferenceConfig) = module {
    single<Preference> {
        PreferenceImpl(
            androidContext(), get(), config
        )
    }
}

private fun utilsModule(config: UtilsConfig) = module {
    single<Utils> {
        UtilsImpl( androidContext(), config )
    }
}