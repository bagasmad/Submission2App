package com.example.submission1app

import android.os.Parcel
import android.os.Parcelable

data class User(
    val users: List<UserDatas>
)

data class  UserDatas(
    val username: String?,
    val name: String?,
    val avatar: String?,
    val company: String?,
    val location: String?,
    val repository: Int,
    val follower: Int,
    val following: Int
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, p1: Int) {
        parcel.writeString(username)
        parcel.writeString(name)
        parcel.writeString(avatar)
        parcel.writeString(company)
        parcel.writeString(location)
        parcel.writeInt(repository)
        parcel.writeInt(follower)
        parcel.writeInt(following)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<UserDatas> {
        override fun createFromParcel(parcel: Parcel): UserDatas {
            return UserDatas(parcel)
        }

        override fun newArray(size: Int): Array<UserDatas?> {
            return arrayOfNulls(size)
        }
    }
}

