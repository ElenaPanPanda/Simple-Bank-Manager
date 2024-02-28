package com.example.simplebankmanager.fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.example.simplebankmanager.R
import com.example.simplebankmanager.databinding.FragmentPayBillsBinding
import com.example.simplebankmanager.getFormatted
import com.example.simplebankmanager.paybills.CodeInfo
import com.example.simplebankmanager.paybills.CodeMap
import com.example.simplebankmanager.paybills.defaultBillInfoMap
import com.example.simplebankmanager.paybills.isRight
import com.example.simplebankmanager.persondata.DefaultPerson


class PayBillsFragment : Fragment(R.layout.fragment_pay_bills) {
    private lateinit var binding: FragmentPayBillsBinding

    private val defaultCodeMap = CodeMap().create(defaultBillInfoMap)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPayBillsBinding.bind(view)


        binding.showBillBtn.setOnClickListener {
            val code = binding.payBillCodeEt.text.toString().uppercase()

            if (code.isRight(defaultCodeMap)) {
                showBillInfoDialog(code)
            } else {
                showNotRightCodeDialog()
            }

        }

    }

    // if code is not right
    private fun showNotRightCodeDialog() {
        AlertDialog.Builder(requireContext())
            .setTitle(R.string.error)
            .setMessage(R.string.wrong_code)
            .setPositiveButton(R.string.ok, null)
            .show()
    }

    // if code is right
    private fun showBillInfoDialog(code: String) {
        val codeInfo = defaultCodeMap[code]!!
        val message1 = getString(R.string.name, codeInfo.fullName)
        val message2 = getString(R.string.bill_code, codeInfo.code)
        val message3 = getString(R.string.amount, codeInfo.amount.getFormatted())


        AlertDialog.Builder(requireContext())
            .setTitle(R.string.bill_info)
            .setMessage(
                "${message1}\n" + "$message2\n" + message3
            )
            .setPositiveButton(R.string.confirm) { _, _ ->
                tryToPayBills(codeInfo)
            }
            .setNegativeButton(R.string.cancel, null)
            .show()
    }

    private fun tryToPayBills(codeInfo: CodeInfo) {
        val amountToPay = codeInfo.amount.getFormatted()

        if (DefaultPerson.balance.enoughToPay(amountToPay)) {
            payBill(amountToPay)
            successfulPaymentToast(codeInfo)
        } else {
            unsuccessfulPaymentDialog()
        }
    }

    private fun payBill(amount: String) = DefaultPerson.balance.withdraw(amount)

    private fun successfulPaymentToast(codeInfo: CodeInfo) {
        val text = getString(R.string.payment_was_successful, codeInfo.fullName)
        Toast.makeText(requireContext(), text, Toast.LENGTH_LONG).show()
    }

    private fun unsuccessfulPaymentDialog() {
        AlertDialog.Builder(requireContext())
            .setTitle(R.string.error)
            .setMessage(R.string.not_enough_funds)
            .setPositiveButton(R.string.ok, null)
            .show()
    }
}