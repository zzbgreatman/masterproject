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
import com.example.dapp.utils.HelperFunctions


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
            layout.findViewById<View>(R.id.SignTypedDataButtonsLayout).visibility = View.VISIBLE
        }

        private fun SignTypedData() {
            val fromAddress: String = HelperFunctions.getTextInput("SignTyped fromAddress: ", R.id.fromAddressInput, layout)
            val toAddress: String = HelperFunctions.getTextInput("SignTyped toAddress: ", R.id.toAddressInput, layout)
            val weiValue: String = HelperFunctions.getTextInput("SignTyped wei: ", R.id.weiValueInput, layout)
            val dataInput: String = HelperFunctions.getTextInput("SignTyped data: ", R.id.dataInput, layout)
            val nonce: String = HelperFunctions.getTextInput("SignTyped nonce: ", R.id.nonceTitle, layout)
            val gasPrice: String = HelperFunctions.getTextInput("SignTyped gas Price: ", R.id.gasPriceInput, layout)
            val gasLimit: String = HelperFunctions.getTextInput("SignTyped gas Limit: ", R.id.gasLimitInput, layout)
            val chainId: String = HelperFunctions.getTextInput("SignTyped chainId: ", R.id.chainIdInput, layout)
            val shouldSubmit: String = HelperFunctions.getTextInput("SignTyped shouldSubmit: ", R.id.shouldSubmitInput, layout)
//            val intent = Intent(dialogContext, MainActivity::class.java)
//            intent.putExtra("fromAddress", fromAddress);
//            startActivity(dialogContext, intent, null);
        }

    }
}