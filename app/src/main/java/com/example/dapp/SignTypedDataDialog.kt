package com.example.dapp

import android.annotation.SuppressLint
import android.app.Dialog
import android.app.PendingIntent.getActivity
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import android.content.Intent
import androidx.core.content.ContextCompat.startActivity


class SignTypedDataDialog(context: Context) : Dialog(context) {

    @SuppressLint("InflateParams")
    class Builder(context: Context) {
        private var contentView: View? = null
        private var closeButtonClickListener: View.OnClickListener? = null
        private var sendButtonClickListener: View.OnClickListener? = null

        private val layout: View
        private companion object val dialog: SignTypedDataDialog = SignTypedDataDialog(context)
        private val dialogContext: Context = context

        init {
            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            inflater.inflate(R.layout.send_typed_data_view, null).also { layout = it }
            dialog.addContentView(layout, ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT))
        }

        fun setCloseButton(listener: View.OnClickListener): Builder {
//            this.singleButtonText = singleButtonText
            this.closeButtonClickListener = listener
            return this
        }

        fun setSendButton(listener: View.OnClickListener): Builder {
//            this.singleButtonText = singleButtonText
            this.sendButtonClickListener = listener
            return this
        }

        fun buildDialog(): SignTypedDataDialog {
            setSendButton{
                SignTypedData()
            }
            showSingleButton()
            layout.findViewById<View>(R.id.send_typedData_close).setOnClickListener(closeButtonClickListener)
            layout.findViewById<View>(R.id.send_typedData_button).setOnClickListener(sendButtonClickListener)

            create()
            return dialog
        }

        private fun create() {
            if (contentView != null) {
                (layout.findViewById<View>(R.id.content) as LinearLayout).removeAllViews()
                (layout.findViewById<View>(R.id.content) as LinearLayout)
                    .addView(contentView, ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT))
            }
            dialog.setContentView(layout)
            dialog.setCancelable(true)
            dialog.setCanceledOnTouchOutside(false)
        }

        private fun showSingleButton() {
//            layout.findViewById<View>(R.id.singleButtonLayout).visibility = View.VISIBLE
            layout.findViewById<View>(R.id.twoButtonLayout).visibility = View.VISIBLE
        }

        private fun SignTypedData() {
            val fromAddress: String = getTextInput("SignTyped fromAddress: ", R.id.fromAddressInput)
            val toAddress: String = getTextInput("SignTyped toAddress: ", R.id.toAddressInput)
            val weiValue: String = getTextInput("SignTyped wei: ", R.id.weiValueInput)
            val dataInput: String = getTextInput("SignTyped data: ", R.id.dataInput)
            val nonce: String = getTextInput("SignTyped nonce: ", R.id.nonceTitle)
            val gasPrice: String = getTextInput("SignTyped gas Price: ", R.id.gasPriceInput)
            val gasLimit: String = getTextInput("SignTyped gas Limit: ", R.id.gasLimitInput)
            val chainId: String = getTextInput("SignTyped chainId: ", R.id.chainIdInput)
            val shouldSubmit: String = getTextInput("SignTyped shouldSubmit: ", R.id.shouldSubmitInput)
//            val intent = Intent(dialogContext, MainActivity::class.java)
//            intent.putExtra("fromAddress", fromAddress);
//            startActivity(dialogContext, intent, null);
        }

        private fun getTextInput(tag: String, fromAddressInput: Int): String {
            var textInput = layout.findViewById<EditText>(fromAddressInput).text.toString();
            Log.v(tag, textInput);
            return textInput
        }

    }
}