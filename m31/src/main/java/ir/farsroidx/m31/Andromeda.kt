@file:Suppress("unused")

package ir.farsroidx.m31

import ir.farsroidx.m31.additives.koinInjection
import ir.farsroidx.m31.app.App
import ir.farsroidx.m31.device.Device
import ir.farsroidx.m31.dispatcher.Dispatcher
import ir.farsroidx.m31.manager.Manager
import ir.farsroidx.m31.memory.Memory
import ir.farsroidx.m31.preference.Preference
import ir.farsroidx.m31.utils.Utils

object Andromeda {

    val app: App               by koinInjection()

    val device: Device         by koinInjection()

    val dispatcher: Dispatcher by koinInjection()

    val manager: Manager       by koinInjection()

    val memory: Memory         by koinInjection()

    val preference: Preference by koinInjection()

    val utils: Utils           by koinInjection()
}