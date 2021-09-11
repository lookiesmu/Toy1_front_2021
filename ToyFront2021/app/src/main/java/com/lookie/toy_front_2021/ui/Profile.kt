package com.lookie.toy_front_2021.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.core.content.edit
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.lookie.toy_front_2021.R
import com.lookie.toy_front_2021.model.UserReceive
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
    private lateinit var logout : Button
    private lateinit var screen : View

    private lateinit var userReceive : UserReceive

    override fun onCreateView(
        inflater : LayoutInflater, container : ViewGroup?,
        savedInstanceState : Bundle?
    ) : View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        viewBinding(view)
        viewHandler()
        back()

        val u = view.context.getSharedPreferences("Muleo", Context.MODE_PRIVATE)
            .getString("username", ".");
        userReceive = model.users.value?.find { userReceive -> userReceive.username == u }!!
        name.setText(userReceive.name)
        phone.setText(userReceive.phone)
        return view
    }

    private fun viewHandler() {
        save.setOnClickListener {
            Toast.makeText(it.context, "미구현", Toast.LENGTH_SHORT).show()
//            val main = (activity as MainActivity)
//            val etpw = pw.text
//            val u = it.context.getSharedPreferences("Muleo", Context.MODE_PRIVATE)
//            model.delete(before = {
//                main.toggleLoading(true)
//                screen.alpha = 0.3f
//            }, after = { b ->
//                screen.alpha = 1f
//                main.toggleLoading(false)
//                if (b) {
//                    u.edit {
//                        putString("password", "$etpw")
//                        apply()
//                    }
//                    findNavController().navigate(R.id.loading)
//                } else {
//                    main.stop { }
//                }
//            }, username = userReceive.username)
        }
        delete.setOnClickListener {
            val main = (activity as MainActivity)
            val u = it.context.getSharedPreferences("Muleo", Context.MODE_PRIVATE)
                .getString("username", ".");
            model.delete(before = {
                main.toggleLoading(true)
                screen.alpha = 0.3f
            }, after = { b ->
                screen.alpha = 1f
                main.toggleLoading(false)
                if (b) {
                    it.context.getSharedPreferences("Muleo", Context.MODE_PRIVATE).edit {
                        putString("username", ".")
                        putString("password", ",")
                        apply()
                    }
                    findNavController().navigate(R.id.loading)
                } else {
                    main.stop {  }
                }
            }, username = u!!)
        }
        logout.setOnClickListener {
            it.context.getSharedPreferences("Muleo", Context.MODE_PRIVATE).edit {
                putString("username", ".")
                putString("password", ",")
                apply()
            }
            findNavController().navigate(R.id.loading)
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
        logout = v.findViewById(R.id.bt_logout)
        screen = v
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