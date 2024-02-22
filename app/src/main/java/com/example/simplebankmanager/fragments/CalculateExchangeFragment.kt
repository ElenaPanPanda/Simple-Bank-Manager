package com.example.simplebankmanager.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.simplebankmanager.exchange.Currency
import com.example.simplebankmanager.R
import com.example.simplebankmanager.databinding.FragmentCalculateExchangeBinding
import com.example.simplebankmanager.exchange.convert
import com.example.simplebankmanager.exchange.toCurrency
import com.example.simplebankmanager.getFormatted


class CalculateExchangeFragment : Fragment(R.layout.fragment_calculate_exchange) {
    private lateinit var binding: FragmentCalculateExchangeBinding


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCalculateExchangeBinding.bind(view)

        // set spinner 1
        ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            listOf(
                Currency.EUR,
                Currency.GBP,
                Currency.USD
            )
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

            binding.spinnerFrom.adapter = adapter
        }

        // set spinner 2
        ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            listOf(
                Currency.EUR,
                Currency.GBP,
                Currency.USD
            )
        )
            .also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

                binding.spinnerTo.adapter = adapter

                val selection = Currency.USD
                val spinnerPosition: Int = adapter.getPosition(selection)
                binding.spinnerTo.setSelection(spinnerPosition)

            }

        binding.calculateBtn.setOnClickListener {
            val currencyFrom = binding.spinnerFrom.selectedItem.toString().toCurrency()
            val currencyTo = binding.spinnerTo.selectedItem.toString().toCurrency()
            val amountToConvert = binding.calculateExchangeEt.text.toString()

            if (
                !theSameCurrency(currencyFrom, currencyTo) &&
                !noValueInput(amountToConvert)
            ) {
                convert(amountToConvert.toDouble(), currencyFrom, currencyTo)
            }
        }

        // reset calculated result
        binding.calculateExchangeEt.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.calculatedResultTv.text = ""
            }

            override fun afterTextChanged(s: Editable?) {}

        })
    }

    private fun theSameCurrency(currencyFrom: Currency, currencyTo: Currency): Boolean {
        return if (currencyFrom == currencyTo) {
            val text = getString(R.string.cannot_convert_to_same_currency)
            Toast.makeText(requireContext(), text, Toast.LENGTH_LONG).show()
            true
        } else false
    }

    private fun noValueInput(amountToConvert: String): Boolean {
        return if (amountToConvert.isBlank()) {
            val text = getString(R.string.enter_amount)
            Toast.makeText(requireContext(), text, Toast.LENGTH_LONG).show()
            true
        } else false
    }

    private fun convert(amountToConvert: Double, currencyFrom: Currency, currencyTo: Currency) {
        val result = amountToConvert.convert(currencyFrom, currencyTo)
        val text = getString(
            R.string.result_conversion,
            currencyFrom.sign,
            amountToConvert.getFormatted(),
            currencyTo.sign,
            result
            )
        binding.calculatedResultTv.text = text
    }
}

