package com.example.simplebankmanager.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.simplebankmanager.R
import com.example.simplebankmanager.databinding.FragmentMenuBinding


class MenuFragment : Fragment(R.layout.fragment_menu) {
    private lateinit var binding: FragmentMenuBinding

    private var userName: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            userName = it.getString(USER_NAME)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMenuBinding.bind(view)

        binding.welcomeTv.text = getString(R.string.welcome_username, userName)

        binding.viewBalanceBtn.setOnClickListener {
            findNavController().navigate(R.id.ViewBalance)
        }

        binding.transferFundsBtn.setOnClickListener {
            findNavController().navigate(R.id.TransferFunds)
        }
    }

    companion object {
        const val USER_NAME = "username"
    }


}