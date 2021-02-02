package com.am.stackexchange.model.data


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Item(
    val tags: List<String>,
    val owner: Owner,
    @SerializedName("is_answered")
    val isAnswered: Boolean,
    @SerializedName("view_count")
    val viewCount: Int,
    @SerializedName("answer_count")
    val answerCount: Int,
    val score: Int,
    @SerializedName("last_activity_date")
    val lastActivityDate: Int,
    @SerializedName("creation_date")
    val creationDate: Int,
    @SerializedName("question_id")
    @PrimaryKey val questionId: Int,
    @SerializedName("content_license")
    val contentLicense: String,
    val link: String,
    val title: String,
    @SerializedName("accepted_answer_id")
    val acceptedAnswerId: Int,
    @SerializedName("last_edit_date")
    val lastEditDate: Int
)