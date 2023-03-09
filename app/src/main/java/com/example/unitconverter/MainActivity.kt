package com.example.unitconverter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.unitconverter.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.convertButton.setOnClickListener { convertAmt() }
    }

    private fun convertAmt() {
        val stringInTextField = binding.ounces.text.toString()
        val startingAmt = stringInTextField.toDoubleOrNull()

        // If the starting amount is null or 0, then display 0 conversion result and exit this function early.
        if (startingAmt == null || startingAmt == 0.0) {
            displayConversionResult(0.0)
            return
        }

        val conversionMultiplier = when (binding.convertOptions.checkedRadioButtonId) {
            R.id.option_grams -> 28.3495
            else -> 0.0625
        }
        var conversionResult = conversionMultiplier * startingAmt
        if (binding.roundUpSwitch.isChecked) {
            conversionResult = kotlin.math.ceil(conversionResult)
        }
        val formattedConversionResult = NumberFormat.getNumberInstance().format(conversionResult)
        binding.conversionResults.text = getString(R.string.conversion, formattedConversionResult)
//        binding.conversionResults.text = getString(R.string.conversion, formattedConversionResult)
        displayConversionResult(conversionResult)
    }

    private fun displayConversionResult(conversionResult : Double) {
        val formattedConversionResult = NumberFormat.getNumberInstance().format(conversionResult)
        binding.conversionResults.text = getString(R.string.conversion, formattedConversionResult)
    }
}