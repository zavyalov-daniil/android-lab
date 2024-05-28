package com.example.android_lab.repository

import java.math.BigInteger
import java.security.MessageDigest

object MarvelApiConstants {
    const val MAX_RESULT = "10"
    const val BASE_URL = "https://gateway.marvel.com"
    const val PUBLIC_API_KEY = "680932109bf5774cfc75d9b1e33f27b1"
    const val PRIVATE_API_KEY = "f233a7df539b71c227805676245e8ad0b6908d65"
    const val TS = "1"

    fun createHash(): String {
        val strToMD = "$TS$PRIVATE_API_KEY$PUBLIC_API_KEY"
        val md5 = MessageDigest.getInstance("MD5")

        return BigInteger(1, md5.digest(strToMD.toByteArray()))
            .toString(16)
            .padStart(32, '0')
    }
}