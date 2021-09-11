package com.lookie.toy_front_2021.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.lookie.toy_front_2021.R
import com.lookie.toy_front_2021.model.Question
import com.lookie.toy_front_2021.viewmodel.MainViewModel


class AnswerList : Fragment() {

    private val model : MainViewModel by activityViewModels()

    private lateinit var recycler : RecyclerView
    private lateinit var screen : View
    private lateinit var adapter : AnswerListAdapter

    private lateinit var questionIdTv : TextView
    private lateinit var questionContentTv : TextView
    private lateinit var toSearch : ImageButton

    val args : AnswerListArgs by navArgs()

    override fun onCreateView(
        inflater : LayoutInflater, container : ViewGroup?,
        savedInstanceState : Bundle?
    ) : View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_answer_list, container, false)
        (activity as MainActivity).toggleCalendar(true)
        model.currentQNUm = args.qNum

        viewBinding(view)

        val question : Question
        // 질문 번호와 질문 내용을 적습니다
        if (args.qNum == -1L) { // 오늘의 질문 경우
            question = model.questions.value!!.last()
            questionIdTv.text = question.q_num.toString()
            questionContentTv.text = question.content
        } else { // 과거의 질문 경우
            val qNum = args.qNum
            question = model.questions.value!!.find { it.q_num == qNum }!!
            questionIdTv.text = question.q_num.toString()
            questionContentTv.text = question.content
        }

        recycler.layoutManager = LinearLayoutManager(view.context)
        adapter = AnswerListAdapter(model = model, itemHandler = { a_num ->
            // TODO Answer 를 선택 했을시 수행하는 핸들러
            // 지금은 기능이 없음.
        }, q = question)
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
        toSearch.setOnClickListener {
            findNavController().navigate(R.id.action_answerList_to_search)
        }
    }

    private fun viewBinding(v : View) {
        recycler = v.findViewById(R.id.recycler_view)
        questionIdTv = v.findViewById(R.id.question_id)
        questionContentTv = v.findViewById(R.id.question_content)
        toSearch = v.findViewById(R.id.toSearch)
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