package com.example.simplebankmanager.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.simplebankmanager.persondata.DefaultPerson
import com.example.simplebankmanager.R
import com.example.simplebankmanager.databinding.FragmentLoginBinding


class LoginFragment : Fragment(R.layout.fragment_login) {
    private lateinit var binding: FragmentLoginBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLoginBinding.bind(view)

        binding.loginBtn.setOnClickListener {
            if (dataIsCorrect())
                logIn()

        }

    }

    private fun dataIsCorrect(): Boolean {
        return checkUserName()
                &&
                checkPassword()
    }


    private fun checkUserName(): Boolean {
        Log.d("msg", "In check Username")
        val inputUsername = binding.usernameEt.text.toString()

        return if (!DefaultPerson.checkUsername(inputUsername)) {
            val errorText = getString(R.string.invalid_username)
            Toast.makeText(requireContext(), errorText, Toast.LENGTH_LONG).show()
            binding.usernameEt.error = errorText
            false
        } else true
    }

    private fun checkPassword(): Boolean {
        Log.d("msg", "In check password")

        val inputPassword = binding.passwordEt.text.toString()
        return if (!DefaultPerson.checkPassword(inputPassword)) {
            val errorText = getString(R.string.invalid_password)
            Toast.makeText(requireContext(), errorText, Toast.LENGTH_LONG).show()
            binding.passwordEt.error = errorText
            false
        } else true
    }


    private fun logIn() {
        Toast.makeText(requireContext(), "logged in", Toast.LENGTH_LONG).show()

        val bundle = Bundle()
        bundle.putString(MenuFragment.USER_NAME, binding.usernameEt.text.toString())
        findNavController().navigate(R.id.MenuFragment, bundle)
    }


}