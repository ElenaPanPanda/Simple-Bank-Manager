package com.example.simplebankmanager.exchange

val defaultMap = mapOf(
    Currency.EUR to mapOf(
        Currency.GBP to 0.5,
        Currency.USD to 2.0
    ),
    Currency.GBP to mapOf(
        Currency.EUR to 2.0,
        Currency.USD to 4.0
    ),
    Currency.USD to mapOf(
        Currency.EUR to 0.5,
        Currency.GBP to 0.25
    )
)