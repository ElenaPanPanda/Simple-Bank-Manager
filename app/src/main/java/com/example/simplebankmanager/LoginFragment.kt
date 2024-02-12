package com.example.simplebankmanager

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.simplebankmanager.databinding.FragmentLoginBinding


class LoginFragment : Fragment(R.layout.fragment_login) {
    private lateinit var binding: FragmentLoginBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLoginBinding.bind(view)

        binding.loginBtn.setOnClickListener {
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

        logIn()

    }

    private fun isRightUserName(): Boolean {
        return binding.username.text.toString() == DefaultData.user_name
    }

    private fun isRightPassword(): Boolean {
        return binding.password.text.toString() == DefaultData.password
    }

    private fun logIn() {
        Toast.makeText(requireContext(), "logged in", Toast.LENGTH_LONG).show()

        val bundle = Bundle()
        bundle.putString(MenuFragment.USER_NAME, binding.username.text.toString())
        findNavController().navigate(R.id.MenuFragment, bundle)
    }


}