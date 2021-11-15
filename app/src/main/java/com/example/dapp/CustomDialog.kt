package com.example.dapp

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView

class CustomDialog(context: Context) : Dialog(context) {

    @SuppressLint("InflateParams")
    class Builder(context: Context) {
        private var contentView: View? = null
        private var image:Bitmap? = null
        private var singleButtonText: String? = null
        private var singleButtonClickListener: View.OnClickListener? = null

        private val layout: View
        private val dialog: CustomDialog = CustomDialog(context)

        init {
            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            inflater.inflate(R.layout.start_connection_dialog, null).also { layout = it }
            dialog.addContentView(layout, ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT))
        }

        fun setImage(bm : Bitmap ): Builder {
            this.image = bm
            return this
        }

        fun setSingleButton(singleButtonText: String, listener: View.OnClickListener): Builder {
            this.singleButtonText = singleButtonText
            this.singleButtonClickListener = listener
            return this
        }

        fun createSingleButtonDialog(): CustomDialog {
            showSingleButton()
            layout.findViewById<View>(R.id.singleBtn).setOnClickListener(singleButtonClickListener)
            if (singleButtonText != null) {
                (layout.findViewById<View>(R.id.singleBtn) as TextView).text = singleButtonText
            } else {
                (layout.findViewById<View>(R.id.singleBtn) as TextView).text = "OK"
            }
            create()
            return dialog
        }

        private fun create() {
            if (contentView != null) {
                (layout.findViewById<View>(R.id.content) as LinearLayout).removeAllViews()
                (layout.findViewById<View>(R.id.content) as LinearLayout)
                    .addView(contentView, ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT))
            } else if (image != null) {
                (layout.findViewById<View>(R.id.imageView) as ImageView).setImageBitmap(image)
            }
            dialog.setContentView(layout)
            dialog.setCancelable(true)
            dialog.setCanceledOnTouchOutside(false)
        }

        private fun showSingleButton() {
            layout.findViewById<View>(R.id.singleButtonLayout).visibility = View.VISIBLE
            layout.findViewById<View>(R.id.twoButtonLayout).visibility = View.GONE
        }

    }
}