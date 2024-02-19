package com.example.simplebankmanager

import java.lang.NumberFormatException

class AccountNumber(val account: String) {

    fun checkAccountNumber(): Boolean {
        if (!checkLength()) return false

        val startLetters = getStartLetters()
        val accountDigits = getAccountDigits()

        if (!checkStartLetters(startLetters)) return false

        if (!checkAccountDigits(accountDigits)) return false

        return true
    }

    private fun getStartLetters(): String {
        return "${account[0]}" +
                "${account[1]}"
    }

    private fun getAccountDigits(): String {
        return "${account[2]}" +
                "${account[3]}" +
                "${account[4]}" +
                "${account[5]}"
    }

    private fun checkLength(): Boolean {
        return account.length == ACCOUNT_NUMBER_LENGTH
    }

    private fun checkStartLetters(s: String): Boolean {
        return s == SA || s == CA
    }

    private fun checkAccountDigits(s: String): Boolean {
        if (s.length != DIGITS_LENGTH) return false

        try {
            s.toInt()
        } catch (e: NumberFormatException) {
            return false
        }

        return true
    }

    companion object {
        const val SA = "sa"
        const val CA = "ca"
        const val ACCOUNT_NUMBER_LENGTH = 6
        const val DIGITS_LENGTH = 4
    }
}