package com.lookie.toy_front_2021.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.lookie.toy_front_2021.R
import com.lookie.toy_front_2021.viewmodel.LoadingViewModel

class LoadingFragment : Fragment() {

    companion object {
        fun newInstance() = LoadingFragment()
    }

    private lateinit var viewModel : LoadingViewModel

    override fun onCreateView(
        inflater : LayoutInflater, container : ViewGroup?,
        savedInstanceState : Bundle?
    ) : View? {
        Log.d("LoadingFragment", "로딩프레그먼트 시작")
        // 로딩 화면이므로 와 bottom nav를 숨깁니다.
//        (activity as MainActivity).toggleBar(on = false)
        val view = inflater.inflate(R.layout.loading_fragment, container, false)

        view.findViewById<TextView>(R.id.GoToId).apply {
            setOnClickListener {
                it.findNavController().navigate(R.id.action_loadingFragment_to_login)
            }
        }

        return view
    }

    override fun onAttach(context : Context) {
        super.onAttach(context)
        viewModel = ViewModelProvider(this).get(LoadingViewModel::class.java)
    }


}