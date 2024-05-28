package com.example.android_lab.repository

import java.math.BigInteger
import java.security.MessageDigest

object MarvelApiConstants {
    const val MAX_RESULT = "10"
    const val BASE_URL = "https://gateway.marvel.com"
    const val PUBLIC_API_KEY = "6b27749315b0b8b0c4bcf7ab8ca1f8ff"
    const val PRIVATE_API_KEY = "11dab13270dda1d7cff1e7f93ef3b9ce2c0be681"
    val TS = "1"

    fun createHash(): String {
        val strToMD = "$TS$PRIVATE_API_KEY$PUBLIC_API_KEY"
        val md5 = MessageDigest.getInstance("MD5")

        return BigInteger(1, md5.digest(strToMD.toByteArray()))
            .toString(16)
            .padStart(32, '0')
    }
}