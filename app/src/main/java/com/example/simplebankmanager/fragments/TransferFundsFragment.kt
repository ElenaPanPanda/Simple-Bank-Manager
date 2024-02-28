package com.example.simplebankmanager.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import com.example.simplebankmanager.transfering.AccountNumber
import com.example.simplebankmanager.transfering.EnterAmount
import com.example.simplebankmanager.R
import com.example.simplebankmanager.databinding.FragmentTransferFundsBinding
import com.example.simplebankmanager.getFormatted
import com.example.simplebankmanager.persondata.DefaultPerson

class TransferFundsFragment : Fragment(R.layout.fragment_transfer_funds) {
    private lateinit var binding: FragmentTransferFundsBinding
    private lateinit var inputAmount: EnterAmount
    private lateinit var accountNumber: AccountNumber

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentTransferFundsBinding.bind(view)


        binding.transferBtn.setOnClickListener {

            if (checkInput()) {

                // compare amount to current balance
                if (DefaultPerson.balance.enoughToPay(inputAmount.amount)) {
                    transfer()

                    // back to menu
                    requireActivity().onBackPressedDispatcher.onBackPressed()
                } else {
                    val text =
                        getString(R.string.unsuccessful_transfer, inputAmount.amount.getFormatted())
                    Toast.makeText(requireContext(), text, Toast.LENGTH_LONG).show()
                }

            }
        }
    }

    private fun checkInput(): Boolean {
        return checkAccountNumber()
                &&
                checkAmountToTransfer()
    }

    private fun checkAccountNumber(): Boolean {
        accountNumber = AccountNumber(binding.accountNumberEt.text.toString())

        return if (accountNumber.checkAccountNumber()) {
            true
        } else {
            binding.accountNumberEt.error = "Invalid account number"
            false
        }
    }

    private fun checkAmountToTransfer(): Boolean {
        inputAmount = EnterAmount(binding.amountToTransferEt.text.toString())

        return if (inputAmount.checkAmountToTransfer()) {
            true
        } else {
            binding.amountToTransferEt.error = "Invalid amount"
            false
        }
    }

    private fun transfer() {
        DefaultPerson.balance.withdraw(inputAmount.amount)

        val text = getString(
            R.string.successful_transfer,
            inputAmount.amount.getFormatted(),
            accountNumber.account
        )

        Toast.makeText(requireContext(), text, Toast.LENGTH_LONG).show()
    }
}