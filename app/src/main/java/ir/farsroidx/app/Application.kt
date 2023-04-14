package ir.farsroidx.app

import ir.farsroidx.m31.AndromedaApplication
import ir.farsroidx.m31.AndromedaConfig
import ir.farsroidx.m31.AndromedaTimeUnit
import ir.farsroidx.m31.additives.*

class Application : AndromedaApplication() {

    /**
     * If the settings for each part are not added,
     * you cannot use that part and you will encounter a crash!
     */
    override val andromedaModules: List<AndromedaConfig> = listOf(

        // TODO ( Recommended )
        // TODO: if you want use of app module
        configAppModule {

        },

        // TODO: if you want use of device module
        configDeviceModule {

        },

        // TODO: if you want use of exception handler module
        configExceptionHandlerModule {
            setDeveloperEmail("mohammadali.riazati@yahoo.com")
        },

        // TODO: if you want use of memory module
        configMemoryModule {
            setExpirationTime(5, AndromedaTimeUnit.Minutes)
        },

        // TODO: if you want use of preference module
        configPreferenceModule {
            setPreferenceName("farsroidx")
            setExpirationTime(1, AndromedaTimeUnit.Day)
        },

        // TODO: if you want use of utils module
        configUtilsModule {

        }
    )
}