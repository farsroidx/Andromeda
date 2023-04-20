package ir.farsroidx.m31.manager

import android.content.Context
import ir.farsroidx.m31.manager.contact.Contact
import ir.farsroidx.m31.manager.contact.ContactImpl

internal class ManagerImpl(
    private val context: Context,
    private val config: ManagerConfig
) : Manager {

    override val contact: Contact = ContactImpl( context )

}