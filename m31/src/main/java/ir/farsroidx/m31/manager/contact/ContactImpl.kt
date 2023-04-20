package ir.farsroidx.m31.manager.contact

import android.content.Context
import android.provider.ContactsContract

internal class ContactImpl(
    private val context: Context
) : Contact {

    companion object {
        private const val NAME             = ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME
        private const val NUMBER           = ContactsContract.CommonDataKinds.Phone.NUMBER
        private const val NORMALIZED       = ContactsContract.CommonDataKinds.Phone.NORMALIZED_NUMBER
        private const val HAS_PHONE_NUMBER = ContactsContract.Contacts.HAS_PHONE_NUMBER
    }

    private val prefixes = listOf(
        // همراه اول
        "0910", "0911", "0912", "0913", "0914", "0915", "0916", "0917", "0918", "0919",
        "0990", "0991", "0992", "0993", "0994", "0995", "0996",
        // ایرانسل
        "0930", "0933", "0935", "0936", "0937", "0938", "0939",
        "0900", "0901", "0902", "0903", "0904", "0905", "0941",
        // شاتل
        "099810", "099811", "099812", "099814", "099815",
        // سامانتل
        "099999", "099998", "099997", "099996",
        // رایتل
        "0920", "0921", "0922", "0923",
        // آپتل
        "099910", "099911", "099913",
        // آسیاتک
        "0942", "0941",
        // آذرتل
        "099914",
        // لوتوس تل
        "09990",
        // آرین تل
        "09998",
        // تالیا
        "0932",
        // اسپادان
        "0931",
        // تله کیش
        "0934"
    )

    override fun getAllContacts(block: ( List<ContactModel> ) -> Unit) {

        val contentResolver = context.contentResolver

        val projection = arrayOf(
            NAME,
            NUMBER,
            NORMALIZED,
            HAS_PHONE_NUMBER
        )

        val phonesCursor = contentResolver.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI, projection,
            HAS_PHONE_NUMBER,
            null, "$NAME COLLATE LOCALIZED ASC"
        )

        var numbersAlreadyFound: HashSet<String>? = HashSet()

        phonesCursor?.let { cursor ->

            if (cursor.count > 0) {

                val contacts = mutableListOf<ContactModel>()

                val nameIndex  = cursor.getColumnIndex(NAME)
                val phoneIndex = cursor.getColumnIndex(NUMBER)

                val normalizerIndex = cursor.getColumnIndex(NORMALIZED)

                while (cursor.moveToNext()) {

                    val normalizedNumber = cursor.getString(normalizerIndex)

                    if (numbersAlreadyFound!!.add(normalizedNumber)) {

                        val phoneName = cursor.getString(nameIndex)
                        var phoneNumber = cursor.getString(phoneIndex)

                        phoneNumber = phoneNumber.replace(" ", "")

                        if (phoneNumber.startsWith("+98")) {
                            phoneNumber = phoneNumber.replace("+98", "0")
                        }

                        contacts.add(
                            ContactModel(
                                name = phoneName,
                                phone = phoneNumber,
                                isVerified = (phoneNumber == "09304025395"),
                            )
                        )
                    }
                }

                block( contacts.distinctBy{ it.phone } )
            }

            cursor.close()
        }

        numbersAlreadyFound = null
    }

    override fun getIranMobilePrefixes(): List<String> = prefixes
}