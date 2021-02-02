package com.am.stackexchange.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.am.stackexchange.model.StackExchangeRepository
import com.am.stackexchange.model.CustomResponse
import com.am.stackexchange.model.data.Question

class StackExchangeViewModel @ViewModelInject constructor(
    repository: StackExchangeRepository
) : ViewModel() {

    val questions: LiveData<CustomResponse<Question>> = repository.getQuestions()

}