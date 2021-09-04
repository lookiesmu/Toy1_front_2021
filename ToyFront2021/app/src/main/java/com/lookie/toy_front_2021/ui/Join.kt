package com.lookie.toy_front_2021.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.lookie.toy_front_2021.R
import com.lookie.toy_front_2021.viewmodel.MainViewModel


class Join : Fragment() {

    private lateinit var name: EditText
    private lateinit var phone: EditText
    private lateinit var id: EditText
    private lateinit var pw1: EditText
    private lateinit var pw2: EditText
    private lateinit var screen: View

    private val model : MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater : LayoutInflater, container : ViewGroup?,
        savedInstanceState : Bundle?
    ) : View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_join, container, false)

        viewBinding(view)
        viewHandler(view)

        return view
    }

    private fun viewBinding(v: View) {
        name = v.findViewById(R.id.name_join)
        phone = v.findViewById(R.id.phone_join)
        id = v.findViewById(R.id.id_join)
        pw1 = v.findViewById(R.id.password_join)
        pw2 = v.findViewById(R.id.password2_join)
        screen = v
    }

    private fun viewHandler(v: View) {
        v.findViewById<TextView>(R.id.join).apply {
            setOnClickListener {
                val main = (activity as MainActivity)
                model.join(before = {
                    main.toggleLoading(true)
                    screen.alpha = 0.3f
                }, after =  { b ->
                    main.toggleLoading(false)
                    if (b) { // 회원가입 성공시
                        main.toggleBar(true)
                        congratulation(after = {findNavController().navigate(R.id.action_join_to_question)})
                    } else { // 회원가입 실패시
                        main.stop { findNavController().popBackStack() }
                    }
                })
            }
        }
    }

    private fun congratulation(after: () -> Unit) {
        // TODO dialog customizing is needed
        MaterialAlertDialogBuilder(requireContext())
            .setMessage("축하합니다!")
            .setPositiveButton("예") { _, _ ->
                after()
            }
            .setOnCancelListener {
                after()
            }
            .show()
    }
}