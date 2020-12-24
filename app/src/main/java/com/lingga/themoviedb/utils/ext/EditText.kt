package com.lingga.themoviedb.utils.ext

import android.util.Patterns
import android.widget.EditText

fun EditText.checkIsNotEmpty(): Boolean {
    if (text.isNullOrEmpty()) error = "Required"
    return !text.isNullOrEmpty()
}

fun EditText.checkIsValidEmailPattern(): Boolean {
    val isEmailPattern = Patterns.EMAIL_ADDRESS.matcher(text.toString()).matches()
    if (!isEmailPattern) {
        error = "Format email not valid"
    }
    return isEmailPattern
}