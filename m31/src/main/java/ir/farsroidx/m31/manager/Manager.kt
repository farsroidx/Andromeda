package ir.farsroidx.m31.manager

import android.Manifest
import androidx.annotation.RequiresPermission
import ir.farsroidx.m31.manager.contact.Contact

interface Manager {

    @get:RequiresPermission(Manifest.permission.READ_CONTACTS)
    val contact: Contact

}