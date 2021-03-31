package com.example.submission1app

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.submission1app.databinding.ActivityMainBinding
import com.loopj.android.http.*
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var adapter: CardViewAdapter
    private lateinit var binding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel
    private var page = 1
    private var q : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mainViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(MainViewModel::class.java)
        adapter = CardViewAdapter(this)
        showRecyclerView()
        binding.buttonLoad.setOnClickListener(this)
        binding.buttonAbout.setOnClickListener(this)
        binding.searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                if (p0!=null && p0!= q)
                {
                    page = 1
                    q=p0
                    showMore()
                }

                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                return true
            }
        })
    }

    private fun showRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
    }

    private fun showMore()
    {
        mainViewModel.setUsersData(q!!,page)
        mainViewModel.getListUserDatas().observe(this, { usersData ->
            if (usersData != null) {
                adapter.setData(usersData)
                adapter.setOnItemClickCallback(object : CardViewAdapter.OnItemClickCallback {
                    override fun onItemClicked(data: UsersData) {
                        val detailIntent = Intent(this@MainActivity, DetailActivity::class.java)
                        detailIntent.putExtra(DetailActivity.EXTRA_USER_DATA, data)
                        startActivity(detailIntent)
                    }

                })
            }
        })
    }

    override fun onClick(button: View?) {
        when (button?.id)
        {
            binding.buttonAbout.id ->
            {
                val aboutIntent = Intent(this@MainActivity, AboutActivity::class.java)
                startActivity(aboutIntent)
            }
            binding.buttonLoad.id ->
            {
                page += 1
                showMore()
            }
        }
    }


//        val client = AsyncHttpClient()

//        client.addHeader("User-Agent","bagasmad")
//        client.get(url, object : AsyncHttpResponseHandler() {
//            override fun onSuccess(statusCode: Int, headers: Array<out Header>?, responseBody: ByteArray?) {
//                val result = String(responseBody!!)
//                val jsonArrayUsers = JSONObject(result).getJSONArray("items")
//                Log.d("ITEMS", jsonArrayUsers.toString())
//                for(i in jsonArray)
//                {
//                    val selectedUser = jsonArrayUsers.getJSONObject(i)
//                }
//            }
//
//            override fun onFailure(statusCode: Int, headers: Array<out Header>?, responseBody: ByteArray?, error: Throwable?) {
//                Log.d("responseFail", statusCode.toString())
//            }
//        })
    }
