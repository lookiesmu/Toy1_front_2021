package com.lookie.toy_front_2021.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.lookie.toy_front_2021.R
import com.lookie.toy_front_2021.viewmodel.MainViewModel

class MypageAdapter(
    private val model : MainViewModel,
    private val itemHandler : (String) -> Unit,
) : RecyclerView.Adapter<MypageAdapter.ViewHolder>() {

    class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        val screen : View = view
        val userName : TextView = view.findViewById(R.id.user_name)
        val userPhone : TextView = view.findViewById(R.id.user_phone)

        init {
            userName
            userPhone
        }
    }

    override fun onCreateViewHolder(parent : ViewGroup, viewType : Int) : ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return ViewHolder(view = view)
    }

    override fun onBindViewHolder(holder : ViewHolder, position : Int) {
        holder.userName.text = model.users.value?.get(position)?.name
        holder.userPhone.text = model.users.value?.get(position)?.phone
        holder.screen.setOnClickListener {
            val uNum = model.users.value?.get(position)?.username ?: ""
            itemHandler(uNum)
        }
        // TODO 핸들러 데이터 확실히 잡히면 다시 작성
    }

    override fun getItemCount() : Int {
        Log.d("MypageAdapter", "${model.users.value}")
        return model.users.value?.size ?: 0
    }
}