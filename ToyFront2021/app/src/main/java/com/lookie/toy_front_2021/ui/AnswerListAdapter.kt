package com.lookie.toy_front_2021.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.lookie.toy_front_2021.R
import com.lookie.toy_front_2021.model.Question
import com.lookie.toy_front_2021.viewmodel.MainViewModel

class AnswerListAdapter(
    private val model : MainViewModel,
    private val itemHandler : (Long) -> Unit,
    private val q : Question,
) : RecyclerView.Adapter<AnswerListAdapter.ViewHolder>() {


    class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        val screen : View = view
        val answerNum : TextView = view.findViewById(R.id.answer_num)
        val answerContent : TextView = view.findViewById(R.id.answer_content)

        init {
            answerNum
            answerContent
        }
    }

    override fun onCreateViewHolder(parent : ViewGroup, viewType : Int) : ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_answer, parent, false)
        return ViewHolder(view = view)
    }

    override fun onBindViewHolder(holder : ViewHolder, position : Int) {
        val a = q.answerList[position]
        holder.answerNum.text = a.a_num.toString()
        holder.answerContent.text = a.content
        holder.screen.setOnClickListener {
            itemHandler(0L)
        }
    }

    override fun getItemCount() : Int {
        return q.answerList.size
    }
}