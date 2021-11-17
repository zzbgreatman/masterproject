package com.example.dapp.utils

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.example.dapp.MainActivity


class HelperFunctions {

    companion object {
        fun getTextInput(tag: String, fromAddressInput: Int, layout: View): String {
            var textInput = layout.findViewById<EditText>(fromAddressInput).text.toString();
            Log.v(tag, textInput);
            return textInput
        }

        fun toastAsync(context: Context, message: String?) {
            println(message)
            Toast.makeText(context, message, Toast.LENGTH_LONG).show()
        }
    }

    val helperFunctions: HelperFunctions = HelperFunctions()

}