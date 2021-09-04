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
            if (field.value!!.isEmpty()) {
                field = value
            }
        }

    var users : MutableLiveData<MutableList<UserReceive>> = MutableLiveData(mutableListOf())
        set(value) {
            if (field.value!!.isEmpty()) {
                field = value
            }
        }

    fun loading(after : (Boolean) -> Unit) {
        viewModelScope.launch {
            delay(3000L) // 서버 체크하는 시간
            // TODO questions 와 users 동시 요청
            after(true) // 무조건 서버 응답이 있음
        }
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
            if (questions.value!!.isEmpty()) {
                delay(3000) // 네트워크 콜
                // TODO 속성 questions 에 set
            }
            after(true) // 일단 무조건 성공임을 표시함.
        }
    }

    fun users(before : () -> Unit, after : (Boolean) -> Unit) {
        viewModelScope.launch {
            before()
            if (questions.value!!.isEmpty()) {
                delay(3000) // 네트워크 콜
                // TODO 속성 users 에 set
            }
            after(true) // 일단 무조건 성공임을 표시함.
        }
    }


}