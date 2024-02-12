package com.example.simplebankmanager

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.simplebankmanager.databinding.FragmentLoginBinding
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
    }

    companion object {
        const val USER_NAME = "username"
    }
}