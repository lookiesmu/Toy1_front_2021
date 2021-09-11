package com.lookie.toy_front_2021.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.lookie.toy_front_2021.R
import com.lookie.toy_front_2021.model.AnswerSimple
import com.lookie.toy_front_2021.viewmodel.MainViewModel

class Answer : Fragment() {

    private val model : MainViewModel by activityViewModels()

    private lateinit var question : TextView
    private lateinit var answer : EditText
    private lateinit var screen : View

    override fun onCreateView(
        inflater : LayoutInflater, container : ViewGroup?,
        savedInstanceState : Bundle?
    ) : View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_answer, container, false)

        viewBinding(view)
        viewHandler(view)

        return view
    }

    private fun viewHandler(v : View) {
        v.findViewById<Button>(R.id.enroll_answer).apply {
            val main = (activity as MainActivity)
            val u = this.context.getSharedPreferences("Muleo", Context.MODE_PRIVATE)
                .getString("username", ".");
            val uNum = model.users.value!!.find { it.username == u }!!.u_num!!
            val qNum = model.questions.value!!.last().let {
                question.text = "${it.q_num}. ${it.content}"
                it.q_num
            }
            setOnClickListener {
                model.answer(before = {
                    main.toggleLoading(true)
                    screen.alpha = 0.3f
                }, after = { b ->
                    main.toggleLoading(false)
                    if (b) { // 답변 성공
                        findNavController().navigate(R.id.action_answer_to_answerList)
                    } else { // 답변 실패
                        screen.alpha = 1f
                        main.stop("답변은 한개만 등록 가능합니다!") { }
                    }
                }, answer = AnswerSimple(answer.text.toString(), u_num = uNum), qNum = qNum)
            }
        }
    }

    private fun viewBinding(v : View) {
        question = v.findViewById(R.id.question)
        answer = v.findViewById(R.id.answer)
        screen = v
    }
}