package com.example.marvel.utils

import com.google.gson.Gson


fun <T> String?.toObject(classOfT: Class<T>): T? = try {
    Gson().fromJson(this, classOfT)
} catch (ex: Exception) {
    null
}