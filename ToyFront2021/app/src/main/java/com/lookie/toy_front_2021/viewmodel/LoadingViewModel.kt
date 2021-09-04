package com.lookie.toy_front_2021.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoadingViewModel : ViewModel() {

    fun loading(after: (Boolean) -> Unit) {
        viewModelScope.launch {
            delay(3000L) // 서버 체크하는 시간
            after(true) // 무조건 서버 응답이 있음
        }
    }
}
