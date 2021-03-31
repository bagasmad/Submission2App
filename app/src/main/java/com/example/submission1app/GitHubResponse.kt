package com.example.submission1app

data class GitHubResponse (
    val total_count: Int?,
    val incomplete_results : Boolean?,
    val items : ArrayList<UsersData>? = null
)