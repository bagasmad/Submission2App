package com.example.submission1app
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        Handler().postDelayed({
            val mainActivityIntent: Intent = Intent(this, MainActivity::class.java)
            startActivity(mainActivityIntent)
            finish()
        },1000)
    }
}