package com.lookie.toy_front_2021.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lookie.toy_front_2021.R
import com.lookie.toy_front_2021.viewmodel.MainViewModel

class Search : Fragment() {

    private val model : MainViewModel by activityViewModels()

    private lateinit var recycler : RecyclerView
    private lateinit var screen : View
    private lateinit var adapter : SearchAdapter

    override fun onCreateView(
        inflater : LayoutInflater, container : ViewGroup?,
        savedInstanceState : Bundle?
    ) : View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_search, container, false)
        (activity as MainActivity).toggleCalendar(true)

        viewBinding(view)
        recycler.layoutManager = LinearLayoutManager(view.context)
        adapter = SearchAdapter(model = model, itemHandler = { qNum ->
            val action = SearchDirections.actionSearchToAnswerList(qNum = qNum)
            findNavController().navigate(action)
        })
        recycler.adapter = adapter

        viewHandler(view)
        return view
    }

    override fun onViewCreated(view : View, savedInstanceState : Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val main = (activity as MainActivity)
        model.questions(before = {
            main.toggleLoading(true)
            screen.alpha = 0.3f
        }, after = { b ->
            screen.alpha = 1f
            main.toggleLoading(false)
            if (!b) {
                main.stop { findNavController().popBackStack() }
            }
        })
    }

    override fun onStop() {
        super.onStop()
        (activity as MainActivity).toggleCalendar(false)
    }

    private fun viewHandler(v : View) {
        model.questions.observe(viewLifecycleOwner, {
            if (it.isNotEmpty()) {
                adapter.notifyDataSetChanged()
            }
        })
    }

    private fun viewBinding(v : View) {
        recycler = v.findViewById(R.id.recycler_view)
        screen = v
    }

}