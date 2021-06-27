package com.eabmodel.juegazosgabazo.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.content.Intent
import com.eabmodel.juegazosgabazo.R

class SplashPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_page)

        Handler(Looper.getMainLooper()).postDelayed(
            {
                val intent = Intent(this, LoginPage::class.java)
                startActivity(intent)
                finish()
            }, 2000
        )

    }
}