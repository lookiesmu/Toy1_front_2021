package com.lookie.toy_front_2021.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.lookie.toy_front_2021.R


class Question : Fragment() {

    override fun onCreateView(
        inflater : LayoutInflater, container : ViewGroup?,
        savedInstanceState : Bundle?
    ) : View? {

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_question, container, false)

        view.findViewById<TextView>(R.id.today).apply {
            setOnClickListener {
                it.findNavController()
                    .navigate(R.id.action_question_to_answer)
            }
        }

        back()

        return view
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