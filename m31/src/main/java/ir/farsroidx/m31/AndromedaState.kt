@file:Suppress("unused")

package ir.farsroidx.m31

object AndromedaState {

    var logEnabled: Boolean = true

    var logStrTag : String  = "CentralCore"

    var currentActivity: AndromedaActivity<*>? = null

    var currentFragment: AndromedaFragment<*>? = null

}