package com.lookie.toy_front_2021.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.lookie.toy_front_2021.R
import com.lookie.toy_front_2021.viewmodel.MainViewModel

class Login : Fragment() {

    private val model : MainViewModel by activityViewModels()


    private lateinit var etid : EditText
    private lateinit var etpw : EditText
    private lateinit var screen : View

    override fun onCreateView(
        inflater : LayoutInflater, container : ViewGroup?,
        savedInstanceState : Bundle?
    ) : View? {

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_login, container, false)
        viewBinding(view)
        viewHandler(view)
        back()
        return view
    }

    private fun viewBinding(v : View) {
        etid = v.findViewById(R.id.login_id)
        etpw = v.findViewById(R.id.login_pw)
        screen = v
    }

    private fun viewHandler(v : View) {
        v.findViewById<TextView>(R.id.join_to).apply {
            setOnClickListener {
                it.findNavController().navigate(R.id.action_login_to_join)
            }
        }
        v.findViewById<TextView>(R.id.login_to).apply {

            setOnClickListener {
                val idt = "${etid.text}"
                val pwt = "${etpw.text}"
                val main = (activity as MainActivity)
                model.login(before = {
                    main.toggleLoading(true)
                    screen.alpha = 0.3f
                }, after = { b ->
                    main.toggleLoading(false)
                    if (b) { // 로그인 성공시
                        val shared =
                            this.context.getSharedPreferences("Muleo", Context.MODE_PRIVATE)
                        shared.edit().apply {
                            putString("username", idt)
                            putString("password", pwt)
                            apply()
                        }
                        main.toggleBar(true)
                        Toast.makeText(context, "어서오세요!", Toast.LENGTH_SHORT).show()
                        findNavController().navigate(R.id.action_login_to_question)
                    } else { // 로그인 실패시
                        screen.alpha = 1f
                        Toast.makeText(context, "로그인 실패!", Toast.LENGTH_SHORT).show()
                    }
                }, id = idt, pw = pwt)
            }
        }
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