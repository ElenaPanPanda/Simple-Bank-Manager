package com.example.simplebankmanager.transfering

import java.lang.NumberFormatException

class EnterAmount(val amount: String) {
    fun checkAmountToTransfer(): Boolean {

        // is number
        try {
            amount.toDouble()
        } catch (e: NumberFormatException) {
            return false
        }

        // is positive number
        if (amount.toDouble() <= 0.0) return false

        return true
    }

    fun enoughFunds(number: String): Boolean = number.toDouble() >= this.amount.toDouble()

}