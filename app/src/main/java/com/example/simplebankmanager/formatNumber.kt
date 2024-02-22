package com.example.simplebankmanager

fun Double.getFormatted(): String {
    val result = String.format("%.2f", this)
    return result.replace(',', '.', false)
}

fun String.getFormatted(): String {
    val result = String.format("%.2f", this.toDouble())
    return result.replace(',', '.', false)
}