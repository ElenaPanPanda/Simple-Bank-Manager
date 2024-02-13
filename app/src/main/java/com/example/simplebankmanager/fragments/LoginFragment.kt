package com.example.simplebankmanager.fragments

import android.os.Bundle
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

        // check username
        val inputUsername = binding.username.text.toString()
        if (!DefaultPerson.checkUsername(inputUsername)) {
            Toast.makeText(requireContext(), R.string.invalid_username, Toast.LENGTH_LONG).show()
            return false
        }

        // check password
        val inputPassword = binding.password.text.toString()
        if (!DefaultPerson.checkPassword(inputPassword)) {
            Toast.makeText(requireContext(), R.string.invalid_password, Toast.LENGTH_LONG).show()
            return false
        }

        return true
    }

    /*private fun isRightUserName(): Boolean {
        return binding.username.text.toString() == DefaultPerson.username
    }*/

    /*private fun isRightPassword(): Boolean {
        return binding.password.text.toString() == DefaultPerson.password
    }*/

    private fun logIn() {
        Toast.makeText(requireContext(), "logged in", Toast.LENGTH_LONG).show()

        val bundle = Bundle()
        bundle.putString(MenuFragment.USER_NAME, binding.username.text.toString())
        findNavController().navigate(R.id.MenuFragment, bundle)
    }


}