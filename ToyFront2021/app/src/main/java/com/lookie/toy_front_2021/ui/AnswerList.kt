package com.lookie.toy_front_2021.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.lookie.toy_front_2021.R
import com.lookie.toy_front_2021.viewmodel.MainViewModel


class AnswerList : Fragment() {

    private val model : MainViewModel by activityViewModels()

    private lateinit var recycler : RecyclerView
    private lateinit var screen : View
    private lateinit var adapter : AnswerListAdapter

    override fun onCreateView(
        inflater : LayoutInflater, container : ViewGroup?,
        savedInstanceState : Bundle?
    ) : View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_answer_list, container, false)
        (activity as MainActivity).toggleCalendar(true)

        viewBinding(view)
        recycler.layoutManager = LinearLayoutManager(view.context)
        adapter = AnswerListAdapter(model = model, itemHandler = { uNum ->
            // TODO User 를 선택 했을시 수행하는 핸들러
            // 지금은 기능이 없음.
        })
        recycler.adapter = adapter
        back()

        viewHandler(view)
        return view
    }

    override fun onStop() {
        super.onStop()
        (activity as MainActivity).toggleCalendar(false)
    }

    private fun viewHandler(v : View) {
        model.users.observe(viewLifecycleOwner, {
            if (it.isNotEmpty()) {
                adapter.notifyDataSetChanged()
            }
        })
    }

    private fun viewBinding(v : View) {
        recycler = v.findViewById(R.id.recycler_view)
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