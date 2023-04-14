@file:Suppress("MemberVisibilityCanBePrivate")

package ir.farsroidx.m31

import android.content.Context
import android.os.Handler
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ir.farsroidx.m31.additives.coroutineExceptionHandler
import ir.farsroidx.m31.additives.koinInjection
import ir.farsroidx.m31.dispatcher.Dispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

abstract class AndromedaViewModel : ViewModel() {

    protected val applicationContext: Context by koinInjection()

    protected val handler: Handler by koinInjection()

    protected val dispatcher: Dispatcher by koinInjection()

    protected fun <T> doInBackground(
        inBackground: suspend ( CoroutineScope ) -> T,
        inForeground: ( T ) -> Unit = {},
        inException: ( Throwable ) -> Unit = {},
    ) = viewModelScope.launch(
        dispatcher.io + coroutineExceptionHandler { throwable ->
            handler.post {
                inException( throwable )
            }
        }
    ) {
        val returnValue = inBackground( this )
        withContext( dispatcher.main ) {
            inForeground( returnValue )
        }
    }
}
