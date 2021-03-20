package com.example.submission1app

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.submission1app.databinding.ActivityAboutBinding


class AboutActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var binding: ActivityAboutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAboutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.buttonBack.setOnClickListener(this)
        binding.buttonGitHub.setOnClickListener(this)
    }

    override fun onClick(button: View?) {
        when (button?.id) {
            (R.id.button_back) -> {
                finish()
            }
            (R.id.button_git_hub) -> {
                val visitWebPageObject = VisitWebPageClass()
                visitWebPageObject.openWebPage("https://github.com/bagasmad", this)
            }
        }
    }
}