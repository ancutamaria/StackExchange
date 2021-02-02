package com.am.stackexchange.model.data


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Owner(
    val reputation: Int,
    @SerializedName("user_id")
    @PrimaryKey val userId: Int,
    @SerializedName("user_type")
    val userType: String,
    @SerializedName("profile_image")
    val profileImage: String,
    @SerializedName("display_name")
    val displayName: String,
    val link: String,
    @SerializedName("accept_rate")
    val acceptRate: Int
)