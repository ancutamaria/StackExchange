package com.am.stackexchange.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.am.stackexchange.model.data.Question
import com.am.stackexchange.model.db.ItemsDao
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class StackExchangeRepository @Inject constructor(
        private val apiService: StackExchangeAPIService
        , private val itemsDao : ItemsDao

){

    fun getQuestions(): LiveData<CustomResponse<Question>>{
        val cachedQuestions = itemsDao.getItems()
        if (cachedQuestions.isNotEmpty()) {
            var question = Question(cachedQuestions, false, 1, 1)
            return MutableLiveData(CustomResponse.OK(question))
        }
        val questionResponse = MutableLiveData<CustomResponse<Question>>()

        apiService.getQuestion().enqueue(object : Callback<Question> {
            override fun onResponse(call: Call<Question>, response: Response<Question>) {
                val responseTmp: Question? = response.body()
                questionResponse.value = CustomResponse.OK(responseTmp)
                if (responseTmp != null) {
                    itemsDao.setItems(responseTmp.items)
                }
            }
            override fun onFailure(call: Call<Question>, t: Throwable) {
                questionResponse.value =
                        CustomResponse.Error("Error on trying to access REST: \n ${t.message}")
            }
        })
        return questionResponse
    }
}
