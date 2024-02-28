package com.example.simplebankmanager.persondata

import com.example.simplebankmanager.getFormatted

class Balance(private var balance: String) {

    fun get(): String = balance

    fun withdraw(amount: String) {
        val result = balance.toDouble() - amount.toDouble()
        balance = result.getFormatted()
    }

    fun enoughToPay(amountToPay: String): Boolean {
        return amountToPay.toDouble() <= balance.toDouble()
    }

}