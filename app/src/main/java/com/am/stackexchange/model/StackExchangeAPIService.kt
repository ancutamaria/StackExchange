package com.am.stackexchange.model

import com.am.stackexchange.model.data.Item
import com.am.stackexchange.model.data.Question
import retrofit2.Call
import retrofit2.http.GET


interface StackExchangeAPIService {

    @GET("questions?order=desc&sort=activity&site=stackoverflow")
    fun getQuestion(): Call<Question>

}