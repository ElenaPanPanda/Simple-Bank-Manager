package com.example.simplebankmanager

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast


class LoginFragment : Fragment(R.layout.fragment_login) {

    private var userNameEditText: EditText? = null
    private var passwordEditText: EditText? = null
    private var buttonLogIn: Button? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userNameEditText = view.findViewById(R.id.username)
        passwordEditText = view.findViewById(R.id.password)
        buttonLogIn = view.findViewById(R.id.login_btn)

        buttonLogIn?.setOnClickListener {
            checkData()
        }

    }

    private fun checkData() {
        if (!isRightUserName()) {
            Toast.makeText(requireContext(), R.string.invalid_username, Toast.LENGTH_LONG).show()
            return
        }

        if (!isRightPassword()) {
            Toast.makeText(requireContext(), R.string.invalid_password, Toast.LENGTH_LONG).show()
            return
        }

        loggedIn()

    }

    private fun isRightUserName(): Boolean {
        return userNameEditText?.text.toString() == DefaultData.user_name
    }

    private fun isRightPassword(): Boolean {
        return passwordEditText?.text.toString() == DefaultData.password
    }

    private fun loggedIn() {
        Toast.makeText(requireContext(), "logged in", Toast.LENGTH_LONG).show()
    }
}