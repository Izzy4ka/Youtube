package com.example.youtube.utils.ext

import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible

fun Context.showToast(message: String) =
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()

fun View.gone() {
    this.isVisible = false
}
fun View.visible() {
    this.isVisible = true
}