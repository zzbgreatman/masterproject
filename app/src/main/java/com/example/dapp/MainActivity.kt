package com.example.dapp

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.dapp.databinding.ActivityMainBinding
import com.example.dapp.utils.SendTransactionListener
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.google.zxing.common.BitMatrix
import org.web3j.crypto.Credentials
import org.web3j.protocol.Web3j
import org.web3j.protocol.core.methods.response.TransactionReceipt
import org.web3j.protocol.infura.InfuraHttpService
import java.lang.Exception
import java.math.BigInteger
import java.util.concurrent.Future


@SuppressLint("StaticFieldLeak")
private var builderForCustom: CustomDialog.Builder? = null
private var mDialog: CustomDialog? = null

private var sendTransactionbuilder: SendTransactionDialog.Builder? = null
private var sendTransactionDialog: SendTransactionDialog? = null

private var signTypedDataDialogBuiler: SignTypedDataDialog.Builder? = null
private var signTypedDataDialog: SignTypedDataDialog? = null

fun barcodeFormatCode(content: String): Bitmap {
    val barcode = BarcodeFormat.QR_CODE
    val matrix = MultiFormatWriter().encode(content, barcode, 1000, 1000, null)
    return matrix2Bitmap(matrix)
}

private fun matrix2Bitmap(matrix: BitMatrix) : Bitmap {
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


class MainActivity : AppCompatActivity(), SendTransactionListener {
    private val TAG = "MyActivity"

    private lateinit var binding: ActivityMainBinding

    private val INFURA_API_ENDPOINT = "https://rinkeby.infura.io/v3/09e8e77f225a40978a15c1bdcd9daf17"
    //FIXME: Add your own password here
    private val password = "Fengzi-infura-690"
    private val toAddress = "0x31B98D14007bDEe637298086988A0bBd31184523"



    @RequiresApi(Build.VERSION_CODES.N)
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

    fun showSignTypedDataDialog(view: android.view.View) {
        var sessionId = "987654321"
        var secret = "HiMyNameIsZiyang"
        var url = "www.walletlink.org"
        var userId = "20616604"
        var metadata = "127.0.0.1"
        var deeplink = "https://${url}/userId=${userId}/secret=${secret}/sessionId=${sessionId}/metadata=${metadata}"
        var testlink = "wc:bc61ec1b-1149-45ef-926d-a5442c60fb60@1?bridge=wss%3A%2F%2Fapi.dydx.exchange%2Fwc%2F&key=650cd910a852c946ff75cd97479492f3f64010dc1258fcf550dce46eb3021ef7"
        signTypedDataDialogBuiler = SignTypedDataDialog.Builder(this)
        sendSignTypedDataDialog(){
            signTypedDataDialog!!.dismiss()
        }

        val value = intent.getStringExtra("fromAddress")
        if (value != null) {
            Log.v("MainActivity", value)
        };
    }

    private fun sendSignTypedDataDialog(onClickListener:View.OnClickListener) {
        signTypedDataDialog = signTypedDataDialogBuiler!!
            .setCloseButton(onClickListener)
            .buildDialog()
        signTypedDataDialog!!.show()
    }

    private fun showSingleButtonDialog(link:String, btnText: String, onClickListener:View.OnClickListener) {
        mDialog = builderForCustom!!
            .setSingleButton(btnText, onClickListener).setImage(barcodeFormatCode(link))
            .createSingleButtonDialog()
        mDialog!!.show()
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun showSendTransactionDialog(view: android.view.View) {
        sendTransactionbuilder = SendTransactionDialog.Builder(this)
        // Set a click listener for button widget
        sendTransactionDialog(){
            sendTransactionDialog!!.dismiss()
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun sendTransactionDialog(onClickListener:View.OnClickListener) {

        sendTransactionDialog = sendTransactionbuilder!!
            .setCloseButton(onClickListener)
            .createDialog()
        sendTransactionDialog!!.show()
    }

    override fun onSend(result: String) {
        toastAsync(result)
    }

    fun toastAsync(message: String?) {
        println(message)
        runOnUiThread {
            Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        }
    }

}