package com.example.dapp

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import com.example.dapp.utils.getTextInput


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
            val fromAddress: String = getTextInput("SignTyped fromAddress: ",
                layout.findViewById<EditText>(R.id.from_address_input))
            val toAddress: String = getTextInput("SignTyped toAddress: ",
                layout.findViewById<EditText>(R.id.to_address_title))
            val weiValue: String = getTextInput("SignTyped wei: ",
                layout.findViewById<EditText>(R.id.wei_input))
            val dataInput: String = getTextInput("SignTyped data: ",
                layout.findViewById<EditText>(R.id.data_input))
            val nonce: String = getTextInput("SignTyped nonce: ",
                layout.findViewById<EditText>(R.id.nonce_input))
            val gasPrice: String = getTextInput("SignTyped gas Price: ",
                layout.findViewById<EditText>(R.id.gas_price_input))
            val gasLimit: String = getTextInput("SignTyped gas Limit: ",
                layout.findViewById<EditText>(R.id.gas_limit_input))
            val chainId: String = getTextInput("SignTyped chainId: ",
                layout.findViewById<EditText>(R.id.chain_id_input))
            val shouldSubmit: String = getTextInput("SignTyped shouldSubmit: ",
                layout.findViewById<EditText>(R.id.should_submit_input))
//            val intent = Intent(dialogContext, MainActivity::class.java)
//            intent.putExtra("fromAddress", fromAddress);
//            startActivity(dialogContext, intent, null);
        }

    }
}