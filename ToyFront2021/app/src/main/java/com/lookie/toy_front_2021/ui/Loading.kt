package com.lookie.toy_front_2021.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.lookie.toy_front_2021.R
import com.lookie.toy_front_2021.viewmodel.MainViewModel

class Loading : Fragment() {

    //    private lateinit var viewModel : LoadingViewModel
    private val model : MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater : LayoutInflater, container : ViewGroup?,
        savedInstanceState : Bundle?
    ) : View? {
        (activity as MainActivity).apply {
            toggleCalendar(on = false)
            toggleBar(on = false)// 로딩 화면이므로 와 bottom nav 를 숨깁니다.
        }
        val view = inflater.inflate(R.layout.fragment_loading, container, false)
        model.loading { b ->
            if (b) { // 서버 응답이 있을 경우
                val isUserLogin = context?.getSharedPreferences(
                    getString(R.string.pref_user_key),
                    Context.MODE_PRIVATE
                )?.getInt("user_num", -1)
                if (isUserLogin == -1) { // 로그인 하지 않았음
                    findNavController().navigate(R.id.action_loading_to_login)
                } else { // 로그인 한 적이 있음
                    findNavController().navigate(R.id.action_loading_to_question)
                }
            } else { // 응답이 없을 경우
                val main = (activity as MainActivity)
                main.stop { main.finish() }
            }
        }
        return view
    }

//    override fun onAttach(context : Context) {
//        super.onAttach(context)
//        viewModel = ViewModelProvider(this)[LoadingViewModel::class.java]
//    }
}