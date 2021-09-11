package com.lookie.toy_front_2021.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lookie.toy_front_2021.model.*
import com.lookie.toy_front_2021.network.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    var token : String? = null

    var currentQNUm : Long? = null

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

    fun loading(username : String, password : String, after : (Boolean) -> Unit) {
        viewModelScope.launch {
            Log.d("MainViewModel", "${username}:${password}")
            if (username != "." && password != ".") {
                try {
                    token = postLogin(UserSimple(username, password))
                    val q = getQuestions(token = token!!)
                    Log.d("MainViewModel", "q: ${q.size}")
                    val u = getUsers(token = token!!)._embedded.userList
                    Log.d("MainViewModel", "u: ${u.size}")
                    questions.value?.clear()
                    questions.value?.addAll(q)
                    users.value?.clear()
                    users.value?.addAll(u)
                    after(true)
                } catch (e : Exception) {
                    after(false)
                    e.printStackTrace()
                    return@launch
                }
            } else {
                after(true)
            }
        }
    }

    fun login(before : () -> Unit, id : String, pw : String, after : (Boolean) -> Unit) {
        viewModelScope.launch {
            before()
            Log.d("MainViewModel", "login ${id}:${pw}")
            try {
                token = postLogin(UserSimple(username = id, password = pw))
                questions.value?.clear()
                questions.value?.addAll(getQuestions(token = token!!))
                users.value?.clear()
                users.value?.addAll(getUsers(token = token!!)._embedded.userList)
                after(true)
            } catch (e : Exception) {
                e.printStackTrace()
                after(false)
            }
        }
    }

    fun join(
        before : () -> Unit,
        name : String,
        phone : String,
        id : String,
        pw1 : String,
        after : (Boolean) -> Unit
    ) {
        viewModelScope.launch {
            before()
            try {
                postUser(UserSend(name = name, phone = phone, username = id, password = pw1))
                token = postLogin(UserSimple(username = id, password = pw1))
                questions.value?.clear()
                questions.value?.addAll(getQuestions(token = token!!))
                users.value?.clear()
                users.value?.addAll(getUsers(token = token!!)._embedded.userList)
                after(true)
            } catch (e : Exception) {
                e.printStackTrace()
                after(false)
            }
        }
    }

    fun answer(before : () -> Unit, after : (Boolean) -> Unit, answer: AnswerSimple, qNum: Long) {
        viewModelScope.launch {
            before()
            try {
                val a = postAnswer(answer = answer, q_num = qNum, token = token!!)
                questions.value!!.find { it.q_num == qNum }!!.answerList.add(a)
                after(true)
            } catch (e: Exception) {
                e.printStackTrace()
                after(false)
            }
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

    fun delete(before : () -> Unit, after : (Boolean) -> Unit, username : String) {
        viewModelScope.launch {
            before()
            try {
                deleteUser(
                    u_num = users.value?.find { userReceive -> userReceive.username == username }?.u_num!!,
                    token = token!!
                )
                after(true)
            } catch (e : Exception) {
                e.printStackTrace()
                after(false)
            }
        }
    }


}