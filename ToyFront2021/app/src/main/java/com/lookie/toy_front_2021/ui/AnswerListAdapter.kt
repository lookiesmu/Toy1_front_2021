package com.lookie.toy_front_2021.ui

import android.content.res.Configuration
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.lookie.toy_front_2021.R
import com.lookie.toy_front_2021.viewmodel.MainViewModel

class AnswerListAdapter(
    private val model : MainViewModel,
    private val itemHandler : (String) -> Unit,
) : RecyclerView.Adapter<AnswerListAdapter.ViewHolder>() {

    class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        val screen : View = view
        val userName : TextView = view.findViewById(R.id.user_name)
        val answer : TextView = view.findViewById(R.id.user_phone)

        init {
            userName
            answer
        }
    }

    override fun onCreateViewHolder(parent : ViewGroup, viewType : Int) : ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_answer, parent, false)
        return ViewHolder(view = view)
    }

    override fun onBindViewHolder(holder : ViewHolder, position : Int) {
        // TODO("Not yet implemented")
    }

    override fun getItemCount() : Int {
        return 0
        // TODO("Not yet implemented")
    }
}