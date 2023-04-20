package ir.farsroidx.m31.manager.contact

interface Contact {

    fun getAllContacts(block: ( List<ContactModel> ) -> Unit)

    fun getIranMobilePrefixes(): List<String>
}