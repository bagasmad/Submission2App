package com.example.submission1app

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import java.io.IOException

class MainActivity : AppCompatActivity() {
    private var list: ArrayList<UserDatas> = arrayListOf()
    private lateinit var userRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fun getJsonFromFile(context: Context, fileName: String): String? {
            val stringFromJson: String
            try {
                //Mencoba untuk parsing data Json menjadi string
                stringFromJson = context.assets.open(fileName).bufferedReader().use{
                    it.readText()
                    }
            }
            catch (ioException: IOException) {
                ioException.printStackTrace()
                return null
            }
            return stringFromJson
        }

        val jsonFileString = getJsonFromFile(applicationContext, "githubuser.json")
        //print(jsonFileString)
        val gson = Gson()
        val usersRoot: User = gson.fromJson(jsonFileString, User::class.java) //parsing JSON menggunakan GSON
        usersRoot.users.forEachIndexed{ _, users -> list.add(users)}
        println(list)
        userRecyclerView = findViewById(R.id.recycler_view)
        showRecyclerView()

        val buttonAbout : ImageView = findViewById(R.id.button_about)
        buttonAbout.setOnClickListener {
            val aboutIntent = Intent(this@MainActivity, AboutActivity::class.java)
            startActivity(aboutIntent)
        }


    }

    private fun showRecyclerView()
    {
        userRecyclerView.layoutManager = LinearLayoutManager(this)
        val listUserAdapter = CardViewAdapter(list)
        userRecyclerView.adapter = listUserAdapter
        listUserAdapter.setOnItemClickCallback(object: CardViewAdapter.OnItemClickCallback
        {
            override fun onItemClicked(data: UserDatas) {
                val detailIntent = Intent(this@MainActivity, DetailActivity::class.java)
                detailIntent.putExtra(DetailActivity.EXTRA_USER_DATA,data)
                startActivity(detailIntent)
            }

        })
        listUserAdapter.setContext(this)

    }
}