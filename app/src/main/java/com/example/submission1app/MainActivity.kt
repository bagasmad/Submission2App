package com.example.submission1app

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.submission1app.databinding.ActivityMainBinding
import com.google.gson.Gson
import java.io.IOException

class MainActivity : AppCompatActivity() {
    private var list: ArrayList<UserDatas> = arrayListOf()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fun getJsonFromFile(context: Context, fileName: String): String? {
            val stringFromJson: String
            try {
                //Mencoba untuk parsing data Json menjadi string
                stringFromJson = context.assets.open(fileName).bufferedReader().use {
                    it.readText()
                }
            } catch (ioException: IOException) {
                ioException.printStackTrace()
                return null
            }
            return stringFromJson
        }

        val jsonFileString = getJsonFromFile(applicationContext, "githubuser.json")
        val gson = Gson()
        val usersRoot: User =
            gson.fromJson(jsonFileString, User::class.java) //parsing JSON menggunakan GSON
        usersRoot.users.forEachIndexed { _, users -> list.add(users) }
        showRecyclerView()
        binding.buttonAbout.setOnClickListener {
            val aboutIntent = Intent(this@MainActivity, AboutActivity::class.java)
            startActivity(aboutIntent)
        }


    }

    private fun showRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        val listUserAdapter = CardViewAdapter(list)
        binding.recyclerView.adapter = listUserAdapter
        listUserAdapter.setOnItemClickCallback(object : CardViewAdapter.OnItemClickCallback {
            override fun onItemClicked(data: UserDatas) {
                val detailIntent = Intent(this@MainActivity, DetailActivity::class.java)
                detailIntent.putExtra(DetailActivity.EXTRA_USER_DATA, data)
                startActivity(detailIntent)
            }

        })
        listUserAdapter.setContext(this)

    }
}