package com.example.submission1app
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class User(
    val users: List<UserDatas>
)

@Parcelize
data class UserDatas(
    val username: String?,
    val name: String?,
    val avatar: String?,
    val company: String?,
    val location: String?,
    val repository: Int,
    val follower: Int,
    val following: Int
) : Parcelable
