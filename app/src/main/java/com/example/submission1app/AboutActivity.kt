package com.example.submission1app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView

class AboutActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)
        val buttonBack : ImageView = findViewById(R.id.button_back)
        val buttonGitHub : Button = findViewById(R.id.button_git_hub)
        buttonBack.setOnClickListener(this)
        buttonGitHub.setOnClickListener(this)
    }

    override fun onClick(button: View?) {
        when(button?.id)
        {
            (R.id.button_back)->
            {
                finish()
            }
            (R.id.button_git_hub)->
            {
                val visitWebPageObject = VisitWebPageClass()
                visitWebPageObject.openWebPage("https://github.com/bagasmad",this)
            }
        }
    }
}