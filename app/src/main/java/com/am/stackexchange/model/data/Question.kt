package com.am.stackexchange.model.data

import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class Question(
    val items: List<Item>,
    @SerializedName("has_more")
    val hasMore: Boolean,
    @SerializedName("quota_max")
    val quotaMax: Int,
    @SerializedName("quota_remaining")
    val quotaRemaining: Int
)