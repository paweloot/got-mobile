package com.paweloot.gotmobile.utility

import java.security.MessageDigest

class Hasher {
    fun hash(stringToHash: String): String {
        val bytes = stringToHash.toByteArray()
        val md = MessageDigest.getInstance("SHA-256")
        val digest = md.digest(bytes)

        return digest.fold("", { str, it -> str + "%02x".format(it) })
    }
}