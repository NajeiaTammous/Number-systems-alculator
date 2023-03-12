package com.example.numbersystemscalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import java.util.*
import kotlin.math.pow

class MainActivity : AppCompatActivity() {

    private lateinit var hexaText: EditText
    private lateinit var octalText: EditText
    private lateinit var decimalText: EditText
    private lateinit var binaryText: EditText
    private var focusedEditText: EditText? = null
    private var textWatcher: TextWatcher? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupViews()
        textWatcher = CustomTextWatcher()

        hexaText.onFocusChangeListener = CustomFocusListener()
        octalText.onFocusChangeListener = CustomFocusListener()
        binaryText.onFocusChangeListener = CustomFocusListener()
        decimalText.onFocusChangeListener = CustomFocusListener()

    }

    private fun setupViews() {
        hexaText = findViewById(R.id.Hex)
        octalText = findViewById(R.id.Oct)
        binaryText = findViewById(R.id.Bin)
        decimalText = findViewById(R.id.Dec)
    }

    private fun toBinary(input: String, base: Int): String {
        try {
            val decimal = Integer.parseInt(input, base)
            return Integer.toString(decimal, 2)
        } catch (e: NumberFormatException) {
            return ""
        }
    }

    private fun toHexa(input: String, base: Int): String {
        try {
            val decimal = Integer.parseInt(input, base)
            return Integer.toString(decimal, 16)
        } catch (e: NumberFormatException) {
            return ""
        }
    }

    private fun toOctal(input: String, base: Int): String {
        try {
            val decimal = Integer.parseInt(input, base)
            return Integer.toString(decimal, 8)
        } catch (e: NumberFormatException) {
            return ""
        }
    }

    private fun toDecimal(input: String, base: Int): String {
        try {
            val decimal = Integer.parseInt(input, base)
            return decimal.toString()
        } catch (e: NumberFormatException) {
            return ""
        }
    }

    inner class CustomTextWatcher : TextWatcher {
        override fun beforeTextChanged(
            s: CharSequence?,
            start: Int,
            count: Int,
            after: Int
        ) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            when (focusedEditText?.id) {
                hexaText.id -> {
                    octalText.setText(toOctal(focusedEditText?.text.toString(), 16))
                    binaryText.setText(toBinary(focusedEditText?.text.toString(), 16))
                    decimalText.setText(toDecimal(focusedEditText?.text.toString(), 16))
                }
                octalText.id -> {
                    hexaText.setText(toHexa(focusedEditText?.text.toString(), 8))
                    binaryText.setText(toBinary(focusedEditText?.text.toString(), 8))
                    decimalText.setText(toDecimal(focusedEditText?.text.toString(), 8))
                }
                binaryText.id -> {
                    hexaText.setText(toHexa(focusedEditText?.text.toString(), 2))
                    octalText.setText(toOctal(focusedEditText?.text.toString(), 2))
                    decimalText.setText(toDecimal(focusedEditText?.text.toString(), 2))
                }
                decimalText.id -> {
                    hexaText.setText(toHexa(focusedEditText?.text.toString(), 10))
                    octalText.setText(toOctal(focusedEditText?.text.toString(), 10))
                    binaryText.setText(toBinary(focusedEditText?.text.toString(), 10))
                }
            }
        }

        override fun afterTextChanged(s: Editable?) {
        }

    }

    inner class CustomFocusListener : View.OnFocusChangeListener {
        override fun onFocusChange(v: View?, hasFocus: Boolean) {
            if (hasFocus) {
                focusedEditText = v as EditText
                focusedEditText?.addTextChangedListener(textWatcher)
            } else {
                focusedEditText?.removeTextChangedListener(textWatcher)
            }

        }
    }

}

