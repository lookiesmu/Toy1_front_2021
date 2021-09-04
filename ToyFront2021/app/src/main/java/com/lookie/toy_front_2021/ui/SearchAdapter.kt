package com.lookie.toy_front_2021.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.lookie.toy_front_2021.R
import com.lookie.toy_front_2021.viewmodel.MainViewModel

class SearchAdapter(
    private val model: MainViewModel,
    private val itemHandler: (Long) -> Unit,
) : RecyclerView.Adapter<SearchAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val screen: View = view
        val qNum: TextView = view.findViewById(R.id.question_id)
        val content: TextView = view.findViewById(R.id.question_content)
        init {
            qNum
            content
        }
    }

    // create new views
    override fun onCreateViewHolder(parent : ViewGroup, viewType : Int) : ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_question, parent, false)
        return ViewHolder(view = view)
    }

    // replace the contents of a view
    override fun onBindViewHolder(holder : ViewHolder, position : Int) {
        holder.qNum.text = "${model.questions.value?.get(position)?.q_num}"
        holder.content.text = "${model.questions.value?.get(position)?.content}"
        holder.screen.setOnClickListener { // TODO 핸들러 데이터 확실히 잡히면 다시 작성
            itemHandler("${holder.qNum.text}".toLong())
        }
    }

    // return the size of dataset
    override fun getItemCount() : Int {
        return model.questions.value?.size ?: 0
    }
}