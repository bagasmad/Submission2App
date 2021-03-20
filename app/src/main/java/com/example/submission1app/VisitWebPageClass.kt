package com.example.submission1app

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri

class VisitWebPageClass : Activity() {
    //kelas objek yang memiliki metode untuk membuka web page melalui browser
    fun openWebPage(url: String?, context: Context) {
        val webPage: Uri = Uri.parse(url)
        val webIntent = Intent(Intent.ACTION_VIEW, webPage)
        if (webIntent.resolveActivity(context.packageManager) != null) {
            context.startActivity(webIntent)
        }
    }
}