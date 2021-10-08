package com.example.dapp

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.dapp.databinding.ActivityMainBinding
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.google.zxing.common.BitMatrix


@SuppressLint("StaticFieldLeak")
private var builderForCustom: CustomDialog.Builder? = null
private var mDialog: CustomDialog? = null

fun barcodeFormatCode(content: String): Bitmap {
    val barcode = BarcodeFormat.QR_CODE
    val matrix = MultiFormatWriter().encode(content, barcode, 1000, 1000, null)
    return matrix2Bitmap(matrix)
}

private fun matrix2Bitmap(matrix: BitMatrix): Bitmap {
    val w = matrix.width
    val h = matrix.height
    val rawData = IntArray(w * h)

    for (i in 0 until w) {
        for (j in 0 until h) {
            var color = Color.WHITE
            if (matrix.get(i, j)) {
                color = Color.BLACK
            }
            rawData[i + (j * w)] = color
        }
    }

    val bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888)
    bitmap.setPixels(rawData, 0, w, 0, 0, w, h)
    return bitmap
}


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    fun startConnection(view: android.view.View) {
        var sessionId = "987654321"
        var secret = "HiMyNameIsZiyang"
        var url = "www.walletlink.org"
        var userId = "20616604"
        var metadata = "127.0.0.1"
        var deeplink = "https://${url}/userId=${userId}/secret=${secret}/sessionId=${sessionId}/metadata=${metadata}"
        var testlink = "wc:bc61ec1b-1149-45ef-926d-a5442c60fb60@1?bridge=wss%3A%2F%2Fapi.dydx.exchange%2Fwc%2F&key=650cd910a852c946ff75cd97479492f3f64010dc1258fcf550dce46eb3021ef7"
        builderForCustom = CustomDialog.Builder(this)
        showSingleButtonDialog(testlink, "Cancel") {
            mDialog!!.dismiss()
        }
    }

    private fun showSingleButtonDialog(link:String, btnText: String, onClickListener:View.OnClickListener) {
        mDialog = builderForCustom!!
            .setSingleButton(btnText, onClickListener).setImage(barcodeFormatCode(link))
            .createSingleButtonDialog()
        mDialog!!.show()
    }
}