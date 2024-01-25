package com.example.marvel.utils

import com.example.marvel.BuildConfig
import java.math.BigInteger
import java.security.MessageDigest
import java.sql.Timestamp

class Constants {
    companion object {
        const val BASE_URL = "https://gateway.marvel.com/"
        val timeStamp = Timestamp(System.currentTimeMillis()).time.toString()
        const val API_KEY = BuildConfig.API_KEY_PUBLIC
        const val PRIVATE_KEY = BuildConfig.API_KEY_PRIVATE
        const val REPLACE_OLD_STRING = "http"
        const val REPLACE_NEW_STRING = "https"
        fun hash(): String {
            val input = "$timeStamp$PRIVATE_KEY$API_KEY"
            val md = MessageDigest.getInstance("MD5")
            return BigInteger(1,md.digest(input.toByteArray())).toString(16).padStart(32,'0')
        }
    }
}