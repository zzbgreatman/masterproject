package com.example.dapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun startConnection(view: android.view.View) {
        Toast.makeText(this,"Generated Deeplink: ", Toast.LENGTH_SHORT).show()
    }
}