package com.lookie.toy_front_2021.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lookie.toy_front_2021.model.Question
import com.lookie.toy_front_2021.model.UserReceive
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    var questions : MutableLiveData<MutableList<Question>> = MutableLiveData(mutableListOf())
        set(value) {
            field = value
        }

    var users : MutableLiveData<MutableList<UserReceive>> = MutableLiveData(mutableListOf())
        set(value) {
            field = value
        }

    fun login(before : () -> Unit, after : (Boolean) -> Unit) {
        viewModelScope.launch {
            before()
            delay(3000) // 네트워크 콜
            after(true) // 일단 무조건 성공임을 표시함.
        }
    }

    fun join(before : () -> Unit, after : (Boolean) -> Unit) {
        viewModelScope.launch {
            before()
            delay(3000) // 네트워크 콜
            after(true) // 일단 무조건 성공임을 표시함.
        }
    }

    fun answer(before : () -> Unit, after : (Boolean) -> Unit) {
        viewModelScope.launch {
            before()
            delay(3000) // 네트워크 콜
            after(true) // 일단 무조건 성공임을 표시함.
        }
    }

    fun questions(before : () -> Unit, after : (Boolean) -> Unit) {
        viewModelScope.launch {
            before()
            delay(3000) // 네트워크 콜
            // TODO 속성 questions 에 set
            after(true) // 일단 무조건 성공임을 표시함.
        }
    }

    fun users(before : () -> Unit, after : (Boolean) -> Unit) {
        viewModelScope.launch {
            before()
            delay(3000) // 네트워크 콜
            // TODO 속성 users 에 set
            after(true) // 일단 무조건 성공임을 표시함.
        }
    }

}