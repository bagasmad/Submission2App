package com.example.submission1app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class DetailActivity : AppCompatActivity(), View.OnClickListener {
    companion object{
        const val EXTRA_USER_DATA = "user_data"
    }
    lateinit var userData: UserDatas
    lateinit var userURL: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        userData = intent.getParcelableExtra(EXTRA_USER_DATA) as UserDatas
        userURL = "https://github.com/"+userData.username
        val textUsername : TextView = findViewById(R.id.text_username)
        val textRealName : TextView = findViewById(R.id.text_realname)
        val textReplaceableFollowers: TextView = findViewById(R.id.text_replaceable_followers)
        val textReplaceableFollowing : TextView = findViewById(R.id.text_replaceable_following)
        val textReplaceableRepositories : TextView = findViewById(R.id.text_replaceable_repositories)
        val textReplaceableLocation : TextView = findViewById(R.id.text_replaceable_location)
        val textReplaceableCompany : TextView = findViewById(R.id.text_replaceable_company)
        val imageDisplayPicture : ImageView = findViewById(R.id.image_display_picture)
        val buttonGo : Button = findViewById(R.id.button_go)
        val buttonShare : Button = findViewById(R.id.button_share)
        val buttonBack : ImageView = findViewById(R.id.button_back)

        textUsername.text = userData.username
        textRealName.text = userData.name
        textReplaceableFollowers.text = userData.follower.toString()
        textReplaceableFollowing.text = userData.following.toString()
        textReplaceableRepositories.text = userData.repository.toString()
        textReplaceableLocation.text = userData.location
        textReplaceableCompany.text = userData.company
        imageDisplayPicture.setImageResource(this.resources.getIdentifier(userData.avatar?.substring(1), null, this.packageName));

        buttonBack.setOnClickListener(this)
        buttonGo.setOnClickListener(this)
        buttonShare.setOnClickListener(this)

    }

    override fun onClick(button: View?) {
        when(button?.id)
        {
            (R.id.button_go)->
            {
                val visitWebPageObject = VisitWebPageClass()
                visitWebPageObject.openWebPage(userURL,this)

            }
            (R.id.button_share)->
            {
                val sendIntent = Intent(Intent.ACTION_SEND)
                sendIntent.putExtra(Intent.EXTRA_TEXT,"Visit and follow ${userData.name} the developer at $userURL")
                sendIntent.type = "text/plain";
                startActivity(sendIntent)
            }
            (R.id.button_back)->
            {
                finish()
            }
        }
    }
}