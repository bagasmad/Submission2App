package com.example.submission1app

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.submission1app.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity(), View.OnClickListener {
    companion object {
        const val EXTRA_USER_DATA = "user_data"
    }

    private lateinit var userData: UsersData
    private lateinit var userURL: String
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        userData = intent.getParcelableExtra(EXTRA_USER_DATA) as UsersData
//        userURL = "https://github.com/" + userData.username
//        //memasukkan data ke dalam views
          binding.textUsername.text = userData.login
//        binding.textRealname.text = userData.name
//        binding.textReplaceableFollowers.text = userData.follower.toString()
//        binding.textReplaceableFollowing.text = userData.following.toString()
//        binding.textReplaceableRepositories.text = userData.repository.toString()
//        binding.textReplaceableLocation.text = userData.location
//        binding.textReplaceableCompany.text = userData.company


        binding.buttonBack.setOnClickListener(this)
        binding.buttonGo.setOnClickListener(this)
        binding.buttonShare.setOnClickListener(this)

    }

    override fun onClick(button: View?) {
        when (button?.id) {
            (R.id.button_go) -> {
                val visitWebPageObject = VisitWebPageClass()
                visitWebPageObject.openWebPage(userURL, this)
            }
            (R.id.button_share) -> {
                //Membuat intent implicit untuk mengirimkan data berupa teks
//                val sendIntent = Intent(Intent.ACTION_SEND)
//                sendIntent.putExtra(
//                    Intent.EXTRA_TEXT,
////                    "Visit and follow ${userData.name} the developer at $userURL"
//                )
//                sendIntent.type = "text/plain"
//                startActivity(sendIntent)
            }
            (R.id.button_back) -> {
                finish()
            }
        }
    }
}