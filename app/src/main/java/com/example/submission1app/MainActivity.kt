package com.example.submission1app

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.submission1app.databinding.ActivityMainBinding
import com.google.gson.Gson
import java.io.IOException

class MainActivity : AppCompatActivity() {
    private lateinit var adapter: CardViewAdapter
    private lateinit var binding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mainViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(MainViewModel::class.java)
        mainViewModel.setUserDatas(this@MainActivity)
        adapter = CardViewAdapter(this)
        showRecyclerView()
        binding.buttonAbout.setOnClickListener {
            val aboutIntent = Intent(this@MainActivity, AboutActivity::class.java)
            startActivity(aboutIntent)
        }

        mainViewModel.getListUserDatas().observe(this,{userDatas ->
            if (userDatas!=null)
            {
                adapter.setData(userDatas)
                adapter.setOnItemClickCallback(object : CardViewAdapter.OnItemClickCallback {
                    override fun onItemClicked(data: UserDatas) {
                        val detailIntent = Intent(this@MainActivity, DetailActivity::class.java)
                        detailIntent.putExtra(DetailActivity.EXTRA_USER_DATA, data)
                        startActivity(detailIntent)
                    }

                })
            }
        })


    }

    private fun showRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

    }
}