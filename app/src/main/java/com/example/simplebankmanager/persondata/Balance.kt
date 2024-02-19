package com.example.simplebankmanager.persondata

import com.example.simplebankmanager.getFormatted

class Balance(private var balance: String) {

    fun get(): String = balance

    fun withdraw(amount: String) {
        val result = balance.toDouble() - amount.toDouble()
        balance = result.getFormatted()
    }

}