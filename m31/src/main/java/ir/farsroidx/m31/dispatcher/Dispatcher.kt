package ir.farsroidx.m31.dispatcher

import kotlinx.coroutines.CoroutineDispatcher

interface Dispatcher {
    val io: CoroutineDispatcher
    val main: CoroutineDispatcher
    val default: CoroutineDispatcher
    val unconfined: CoroutineDispatcher
}