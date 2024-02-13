package com.example.simplebankmanager.persondata

open class Person(
    val username: String,
    val password: String,
    val balance: Balance
) {
    fun checkUsername(s: String): Boolean {
        return s == username
    }

    fun checkPassword(s: String): Boolean {
        return s == password
    }

}