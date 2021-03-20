package com.example.submission1app

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        //menutup activity splashcreen dengan delay 1 detik dan menjalankan main activity
        Handler().postDelayed({
            val mainActivityIntent: Intent = Intent(this, MainActivity::class.java)
            startActivity(mainActivityIntent)
            finish()
        }, 1000)
    }
}