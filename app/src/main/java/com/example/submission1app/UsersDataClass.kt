package com.example.submission1app
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UsersData(
    val login: String?,
    val avatar_url: String?,
    val repos_url: String?,
    val followers_url: String?,
    val following_url: String?
) : Parcelable


