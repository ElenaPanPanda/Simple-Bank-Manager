package com.example.simplebankmanager

fun Double.getFormatted(): String {
    val string = String.format("%.2f", this)
    return string.replace(',', '.', false)
}