package com.example.simplebankmanager.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.simplebankmanager.persondata.DefaultPerson
import com.example.simplebankmanager.R
import com.example.simplebankmanager.databinding.FragmentViewBalanceBinding


class ViewBalanceFragment : Fragment(R.layout.fragment_view_balance) {
    private lateinit var binding: FragmentViewBalanceBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentViewBalanceBinding.bind(view)

        binding.balanceAmountTv.text = getString(R.string.current_balance, DefaultPerson.balance.get())
    }


}