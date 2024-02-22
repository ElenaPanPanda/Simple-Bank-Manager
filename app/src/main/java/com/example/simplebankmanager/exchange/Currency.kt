package com.example.simplebankmanager.exchange

import com.example.simplebankmanager.getFormatted

enum class Currency(val string: String, val sign: Char) {
    USD("USD", '$'),
    GBP("GBP", '£'),
    EUR("EUR", '€');
}

fun String.toCurrency(): Currency {
    return when (this) {
        Currency.EUR.string -> Currency.EUR
        Currency.GBP.string -> Currency.GBP
        Currency.USD.string -> Currency.USD
        else -> Currency.USD
    }
}


fun Double.convert(currencyFrom: Currency, currencyTo: Currency): String {
    val i = defaultMap[currencyFrom]?.get(currencyTo)

    val result = this * i!!
    return result.getFormatted()
}