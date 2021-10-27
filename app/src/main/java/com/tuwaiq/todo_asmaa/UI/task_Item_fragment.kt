package com.tuwaiq.todo_asmaa.UI

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.tuwaiq.todo_asmaa.R
import com.tuwaiq.todo_asmaa.view.MainViewModel

class task_Item_fragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.task_item_fragment, container, false)
    }

    companion object {
        fun newInstance() = task_Item_fragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
   /*     val txt =view.findViewById<TextView>(R.id.testId)
        super.onViewCreated(view, savedInstanceState)
        val vmTask = ViewModelProvider(this).get(MainViewModel::class.java)

*/
        }
    }
