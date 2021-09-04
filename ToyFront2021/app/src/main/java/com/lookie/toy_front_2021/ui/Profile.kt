package com.lookie.toy_front_2021.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.lookie.toy_front_2021.R
import com.lookie.toy_front_2021.viewmodel.MainViewModel


class Profile : Fragment() {

    private val model : MainViewModel by activityViewModels()

    private lateinit var name : EditText
    private lateinit var pw : EditText
    private lateinit var pw2 : EditText
    private lateinit var phone : EditText
    private lateinit var save : Button
    private lateinit var check : CheckBox
    private lateinit var delete : Button

    override fun onCreateView(
        inflater : LayoutInflater, container : ViewGroup?,
        savedInstanceState : Bundle?
    ) : View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        viewBinding(view)
        viewHandler()
        back()

        return view
    }

    private fun viewHandler() {
        save.setOnClickListener {
            // TODO 유저 정보 업데이트
        }
        delete.setOnClickListener {
            // 유저 정보 삭제
        }
    }

    private fun viewBinding(v : View) {
        name = v.findViewById(R.id.et_name)
        pw = v.findViewById(R.id.et_pw)
        pw2 = v.findViewById(R.id.et_pw2)
        phone = v.findViewById(R.id.et_phone)
        save = v.findViewById(R.id.bt_save)
        check = v.findViewById(R.id.checkBox)
        delete = v.findViewById(R.id.bt_delete)
    }


    private fun back() {
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    MaterialAlertDialogBuilder(requireContext())
                        .setMessage("정말 종료하시겠습니까?")
                        .setNegativeButton("취소") { _, _ ->
                            return@setNegativeButton
                        }
                        .setPositiveButton("예") { _, _ ->
                            activity?.finish()
                        }
                        .show()
                }
            })
    }
}