package com.lingga.themoviedb.utils.ext

import java.text.SimpleDateFormat
import java.util.*

fun String.parseDateWithFormat(format: String): String? {
    val df = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    try {
        val result = df.parse(this) ?: return ""
        val sdf = SimpleDateFormat(format, Locale.getDefault())
        return sdf.format(result)
    } catch (e: java.lang.Exception) {
        e.printStackTrace()
    }
    return this
}