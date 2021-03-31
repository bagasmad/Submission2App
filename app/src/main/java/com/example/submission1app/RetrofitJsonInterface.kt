package com.example.submission1app

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitJsonInterface {
        @GET("search/users?")
        fun getUsersData(@Query("q") q: String, @Query("per_page") per_page: Int = 10, @Query("page") page: Int): Call<GitHubResponse>
}